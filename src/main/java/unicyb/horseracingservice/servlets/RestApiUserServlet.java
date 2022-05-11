package unicyb.horseracingservice.servlets;

import com.google.gson.Gson;
import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.database.dao.UserDaoImpl;
import unicyb.horseracingservice.entity.ResponseResult;
import unicyb.horseracingservice.entity.User;
import unicyb.horseracingservice.service.AuthorizationService;

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

@WebServlet("/users/*")
public class RestApiUserServlet extends HttpServlet {

    private HorseRaceDAO<User> userDAO;
    private AuthorizationService authorizationService;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDaoImpl();
        authorizationService = new AuthorizationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String json = null;
        Gson gson = new Gson();
        User user = getRaceFromUrl(req.getRequestURL());
        if(user != null) {
            json = gson.toJson(user);
        }
        else {
            Vector<User> userVector = userDAO.findAll();
            json = gson.toJson(userVector);
        }
        out.print(json);
        out.close();
    }

    private User getRaceFromUrl(StringBuffer requestUrl){
        Pattern pattern = Pattern.compile("users/(\\s+)");
        Matcher matcher = pattern.matcher(requestUrl);
        User user = null;
        if(matcher.find()){
            try {
                String userName = (matcher.group(1));
                user = userDAO.getObjectByParameter(userName);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        if(authorizationService.hasAuthority(req, "user")) {
            if(Pattern.matches(".*/users/$", req.getRequestURL())) {
                InputStreamReader reader = new InputStreamReader(req.getInputStream());
                ResponseResult responseResult = gson.fromJson(reader, ResponseResult.class);
                User user = userDAO.getObject(responseResult.getIdFirst());
                String new_balance = String.valueOf(user.getBalance() - responseResult.getIdSecond());
                userDAO.updateObject(responseResult.getIdFirst(), new String[]{new_balance});
                reader.close();
            }
        }
    }
}
