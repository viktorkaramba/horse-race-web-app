package unicyb.horseracingservice.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import unicyb.horseracingservice.entity.Horse;
import unicyb.horseracingservice.entity.ResponseResult;
import unicyb.horseracingservice.entity.Coefficient;
import unicyb.horseracingservice.service.AuthorizationService;
import unicyb.horseracingservice.service.CoefficientServiceImpl;
import unicyb.horseracingservice.service.HorseRaceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Servlet for work with coefficients
@WebServlet("/coefficients/*")
public class CoefficientServlet extends HttpServlet {

    private HorseRaceService<Coefficient> coefficientService;
    private AuthorizationService authorizationService;

    @Override
    public void init() throws ServletException {
        super.init();
        coefficientService = new CoefficientServiceImpl();
        authorizationService = new AuthorizationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String json = null;
        Gson gson = new Gson();
        Coefficient coefficient = getRaceFromUrl(req.getRequestURL());
        if(coefficient != null) {
            json = gson.toJson(coefficient);
        }
        else {
            Vector<Coefficient> coefficientVector = coefficientService.findAll();
            json = gson.toJson(coefficientVector);
        }
        out.print(json);
        out.close();
    }

    //Method for get object by id
    private Coefficient getRaceFromUrl(StringBuffer requestUrl){
        Pattern pattern = Pattern.compile("coefficients/(\\d+)");
        Matcher matcher = pattern.matcher(requestUrl);
        Coefficient coefficient = null;
        if(matcher.find()){
            try {
                int id = Integer.parseInt(matcher.group(1));
                coefficient = coefficientService.getObject(id);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return coefficient;
    }

    //Method for get coefficient by race id and horse id
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, JsonIOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(req.getInputStream());
        ResponseResult responseResult = gson.fromJson(reader, ResponseResult.class);
        System.out.println(responseResult.toString());
        Map<Integer, Coefficient> coefficientMap =
                coefficientService.getObjectsByTwoParameters(responseResult.getIdFirst(), responseResult.getIdSecond());
        Coefficient coefficient = null;
        for (Coefficient c : coefficientMap.values()) {
            coefficient = c;
        }
        reader.close();
        String json = gson.toJson(coefficient, Coefficient.class);
        System.out.println(json);
        out.print(json);
    }

    //Method for add coefficient
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException, JsonIOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        if(authorizationService.hasAuthority(req, "bookmaker")) {
            if(Pattern.matches(".*/coefficients/$", req.getRequestURL())) {
                InputStreamReader reader = new InputStreamReader(req.getInputStream());
                Coefficient coefficients = gson.fromJson(reader, Coefficient.class);
                System.out.println(coefficients);
                coefficientService.addObject(coefficients);
                reader.close();
                String json = gson.toJson(coefficients, Coefficient.class);
                out.print(json);
            }
        }
    }
}
