package unicyb.horseracingservice.servlets;

import com.google.gson.Gson;
import unicyb.horseracingservice.entity.ResponseResult;
import unicyb.horseracingservice.entity.Horse;
import unicyb.horseracingservice.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

//Servlet used to make a result
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        if(authorizationService.hasAuthority(req, "admin")) {
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader(req.getInputStream());
            ResponseResult responseResult = gson.fromJson(reader, ResponseResult.class);
            makeResultService.updateWinnersBalance(responseResult.getIdFirst(), responseResult.getIdSecond());
            reader.close();
        }
    }
}
