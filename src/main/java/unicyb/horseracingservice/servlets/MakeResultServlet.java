package unicyb.horseracingservice.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicyb.horseracingservice.entity.BetResult;
import unicyb.horseracingservice.entity.Horse;
import unicyb.horseracingservice.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/make-result/*")
public class MakeResultServlet extends HttpServlet {

    private AuthorizationService authorizationService;
    private HorseRaceService<Horse> horseService;
    private MakeResultService makeResultService;

    @Override
    public void init() throws ServletException{
        super.init();
        horseService = new HorseServiceImpl();
        makeResultService = new MakeResultService();
        authorizationService = new AuthorizationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("Hello make-result");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
//        if(authorizationService.hasAuthority(req, "admin")) {
//            if (Pattern.matches(".*/make-reslut/$", req.getRequestURL())) {
//                int idRace = Integer.parseInt(req.getParameter("idRace"));
//                int idHorse = Integer.parseInt(req.getParameter("idHorse"));
//                System.out.println(idRace + " " + idHorse);
//            }
//        }
        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(req.getInputStream());
        BetResult betResult = gson.fromJson(reader, BetResult.class);
        makeResultService.updateWinnersBalance(betResult.getIdRace(), betResult.getIdHorse());
    }
}
