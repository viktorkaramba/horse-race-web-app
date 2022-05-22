package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.database.dao.UserDaoImpl;
import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.entity.Race;
import unicyb.horseracingservice.entity.User;

import java.util.Map;
import java.util.Vector;

//Service for make result
public class MakeResultService {

    private HorseRaceDAO<User> userHorseRaceDAO = new UserDaoImpl();
    private HorseRaceService<Bet> betService = new BetServiceImpl();
    private HorseRaceService<Race> raceService = new RaceServiceImpl();

    //Method for get all user that win
    public Vector<User> getWinners(int idRace, int idHorse){
        Map<Integer, Bet> idUserMap = betService.getObjectsByTwoParameters(idRace, idHorse);
        Vector<User> userVector = new Vector<>();
        System.out.println(idUserMap.keySet());
        for(Integer idUser: idUserMap.keySet()){
            userVector.add(userHorseRaceDAO.getObject(idUser));
        }
        return userVector;
    }

    //Method for update user balance
    public void updateWinnersBalance(int idRace, int idHorse){
        Map<Integer, Bet> idUserMap = betService.getObjectsByTwoParameters(idRace, idHorse);
        Vector<User> userVector = getWinners(idRace, idHorse);
        for (User user: userVector){
            System.out.println(user.getUsername());
            float win = user.getBalance() + idUserMap.get(user.getID()).getPrice();
            System.out.println(win);
            String new_balance = Float.toString(win);
            userHorseRaceDAO.updateObject(user.getID(), new String[]{new_balance});
        }
        String new_is_over = "TRUE";
        raceService.updateObject(idRace, new String[]{new_is_over});
    }

}
