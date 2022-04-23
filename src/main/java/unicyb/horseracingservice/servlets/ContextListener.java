package unicyb.horseracingservice.servlets;

import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.utils.BetUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ContextListener implements ServletContextListener {
    private Map<Integer, Bet> bets;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        bets = new ConcurrentHashMap<>();
        servletContext.setAttribute("bets", bets);
        List<Bet> betList = BetUtils.generateBets();
        betList.forEach(bet -> this.bets.put(bet.getId(), bet));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        bets = null;
    }
}
