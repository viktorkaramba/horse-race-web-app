package unicyb.horseracingservice.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.utils.BetUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonBetServlet extends HttpServlet {
    private Map<Integer, Bet> bets;

    @Override
    public void init() throws ServletException {
        final Object bet = getServletContext().getAttribute("bets");
        if (!(bet instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("...");
        }else {
            this.bets = (ConcurrentHashMap<Integer, Bet>) bet;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
       final String id = req.getParameter("id");

       if(BetUtils.idIsInvalid(id, bets)){
           resp.sendRedirect(req.getContextPath() + "/");
           return;
       }

       final  Bet bet = bets.get(Integer.parseInt(id));
       final String jsonTask = new ObjectMapper().writeValueAsString(bet);

       resp.setContentType("application/json; charset=UTF-8");
       PrintWriter out = resp.getWriter();
       out.write(jsonTask);
    }
}
