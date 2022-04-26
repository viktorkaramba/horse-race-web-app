package unicyb.horseracingservice.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicyb.horseracingservice.entity.Race;
import unicyb.horseracingservice.service.HorseRaceService;
import unicyb.horseracingservice.service.RaceServiceImpl;

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

@WebServlet("/races/*")
public class RestApiRaceServlet extends HttpServlet {

    private HorseRaceService<Race> raceService;

    @Override
    public void init() throws ServletException{
        super.init();
        raceService = new RaceServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String json = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy HH:mm:ss").create();
        Race race = getRaceFromUrl(req.getRequestURL());
        if(race != null) {
            json = gson.toJson(race);
        }
        else {
            Vector<Race> raceVector = raceService.findAll();
            json = gson.toJson(raceVector);
        }
        out.print(json);
        out.close();
    }

    private Race getRaceFromUrl(StringBuffer requestUrl){
        Pattern pattern = Pattern.compile("races/(\\d+)");
        Matcher matcher = pattern.matcher(requestUrl);
        Race race = null;
        if(matcher.find()){
            try {
                int id = Integer.parseInt(matcher.group(1));
                race = raceService.getObject(id);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return race;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy HH:mm:ss").create();
        if(Pattern.matches("^/races/$", req.getRequestURL())){
            InputStreamReader reader = new InputStreamReader(req.getInputStream());
            Race race = gson.fromJson(reader, Race.class);
            raceService.addObject(race);
            reader.close();
            String json = gson.toJson(race, Race.class);
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
        Race race = getRaceFromUrl(req.getRequestURL());
        if(race != null){
            raceService.deleteObject(race.getId());
        }
        String json = gson.toJson(race, Race.class);
        out.print(json);
    }
}
