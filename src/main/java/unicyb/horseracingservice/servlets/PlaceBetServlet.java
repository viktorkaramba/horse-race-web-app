package unicyb.horseracingservice.servlets;

import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.utils.BetUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PlaceBetServlet extends HttpServlet {
    private Map<Integer, Bet> bets;
    private AtomicInteger idCounter;

    @Override
    public void init() throws ServletException {
        final Object bets = getServletContext().getAttribute("bets");

        if (!(bets instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("Your repo does not initialize!");
        } else {
            this.bets = (ConcurrentHashMap<Integer, Bet>) bets;
        }

        idCounter = (AtomicInteger) getServletContext().getAttribute("idCounter");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

//        if (BetUtils.requestIsValid(req)){
//            final String member = req.getParameter("member");
//            final String price = req.getParameter("price");
//
//            final Bet bet = new Bet();
//            final int id = this.idCounter.getAndIncrement();
//            bet.setId(id);
//            bet.setMember(member);
//            bet.setPrice(new Float(price));
//
//            bets.put(id, bet);
//
//        }

        resp.sendRedirect(req.getContextPath() + "/");
        }
    }

