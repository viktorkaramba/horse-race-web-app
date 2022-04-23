package unicyb.horseracingservice.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import unicyb.horseracingservice.entity.Race;
import unicyb.horseracingservice.entity.sevice.HorseRaceService;
import unicyb.horseracingservice.entity.sevice.HorseRaceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Vector;

@WebServlet("/races")
public class GetRaceServlet extends HttpServlet {
    private HorseRaceService horseRaceService;

    @Override
    public void init() throws ServletException{
        super.init();
        horseRaceService = new HorseRaceServiceImpl();
        System.out.println("In init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Vector<Race> raceVector = horseRaceService.findAllRaces();
        for(Race race :raceVector){
            System.out.println(race.getId()+", "+race.getPlace() + ", " + race.getDate());
        }
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy HH:mm:ss").create();
        if (raceVector.size()>0){
            String json = gson.toJson(raceVector);
            out.print(json);
        }
        out.close();
    }
}
