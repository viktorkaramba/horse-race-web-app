package unicyb.horseracingservice.servlets;

import com.google.gson.Gson;
import unicyb.horseracingservice.entity.Horse;
import unicyb.horseracingservice.service.HorseRaceService;
import unicyb.horseracingservice.service.HorseServiceImpl;


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

//Servlet used for work with bets
@WebServlet("/horses/*")
public class RestApiHorseServlet extends HttpServlet {
    private HorseRaceService<Horse> horseService;

    @Override
    public void init() throws ServletException {
        super.init();
        horseService = new HorseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String json = null;
        Gson gson = new Gson();
        Horse horse = getRaceFromUrl(req.getRequestURL());
        if(horse != null) {
            json = gson.toJson(horse);
        }
        else {
            Vector<Horse> horseVector = horseService.findAll();
            json = gson.toJson(horseVector);
        }
        out.print(json);
        out.close();
    }

    private Horse getRaceFromUrl(StringBuffer requestUrl){
        Pattern pattern = Pattern.compile("horses/(\\d+)");
        Matcher matcher = pattern.matcher(requestUrl);
        Horse horse = null;
        if(matcher.find()){
            try {
                int id = Integer.parseInt(matcher.group(1));
                horse = horseService.getObject(id);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return horse;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        if(Pattern.matches(".*/horses/$", req.getRequestURL())){
            InputStreamReader reader = new InputStreamReader(req.getInputStream());
            Horse horse = gson.fromJson(reader, Horse.class);
            horseService.addObject(horse);
            reader.close();
            String json = gson.toJson(horse, Horse.class);
            System.out.println(json);
            out.print(json);

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
        Horse horse = getRaceFromUrl(req.getRequestURL());
        if(horse != null){
            horseService.deleteObject(horse.getID());
        }
        String json = gson.toJson(horse, Horse.class);
        out.print(json);
    }
}
