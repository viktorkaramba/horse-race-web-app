package unicyb.horseracingservice.utils;

import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.entity.WinBet;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BetUtils {

    public static List<Bet> generateBets(){
        final Bet bet1 = new WinBet(1);
        final Bet bet2 = new WinBet(2);
        final Bet bet3 = new WinBet(3);

        List<Bet> bets = new ArrayList<>();
        bets.add(bet1);
        bets.add(bet2);
        bets.add(bet3);
        return bets;
    }

    public static boolean requestIsValid(HttpServletRequest request) {
        final String title = request.getParameter("member");
        final String description = request.getParameter("price");

        return title != null && title.length() > 0 &&
                description != null && description.length() > 0;
    }

    public static boolean idIsInvalid(final String id, Map<Integer, Bet> repo) {
        return !(id != null &&
                id.matches("^\\d*$") &&
                repo.get(Integer.parseInt(id)) != null);
    }
}
