package unicyb.horseracingservice.servlets;

import com.google.gson.Gson;
import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.service.AuthorizationService;
import unicyb.horseracingservice.service.BetServiceImpl;
import unicyb.horseracingservice.service.HorseRaceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/bets/*")
public class RestApiBetServlet extends HttpServlet {
    private HorseRaceService<Bet> betService;
    private AuthorizationService authorizationService;
    @Override
    public void init() throws ServletException {
        super.init();
        betService = new BetServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String json = null;
        Gson gson = new Gson();
        Bet bet = getRaceFromUrl(req.getRequestURL());
        if(bet != null) {
            json = gson.toJson(bet);
        }
        else {
            Vector<Bet> betVector = betService.findAll();
            json = gson.toJson(betVector);
        }
        out.print(json);
        out.close();
    }

    private Bet getRaceFromUrl(StringBuffer requestUrl){
        Pattern pattern = Pattern.compile("bets/(\\d+)");
        Matcher matcher = pattern.matcher(requestUrl);
        Bet bet = null;
        if(matcher.find()){
            try {
                int id = Integer.parseInt(matcher.group(1));
                bet = betService.getObject(id);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return bet;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        if(authorizationService.hasAuthority(req, "user")) {
            if (Pattern.matches("^/bets/$", req.getRequestURL())) {
                InputStreamReader reader = new InputStreamReader(req.getInputStream());
                Bet bet = gson.fromJson(reader, Bet.class);
                betService.addObject(bet);
                reader.close();
                String json = gson.toJson(bet, Bet.class);
                out.print(json);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        Bet bet = getRaceFromUrl(req.getRequestURL());
        if(bet != null){
            betService.deleteObject(bet.getId());
        }
        String json = gson.toJson(bet, Bet.class);
        out.print(json);
    }
}
