package unicyb.horseracingservice.servlets;

import unicyb.horseracingservice.entity.Bet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GetStartedPageServlet extends HttpServlet {
    private Map<Integer, Bet> bets;

    @Override
    public void init() throws ServletException{
        final Object bets = getServletContext().getAttribute("bets");
        if (!(bets instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("...");
        }else {
            this.bets = (ConcurrentHashMap<Integer, Bet>) bets;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("bets", bets.values());
        req.getRequestDispatcher("/WEB-INF/view/index.html").forward(req, resp);
    }
}
