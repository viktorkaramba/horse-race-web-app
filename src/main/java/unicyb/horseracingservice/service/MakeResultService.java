package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.database.dao.UserDaoImpl;
import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.entity.Race;
import unicyb.horseracingservice.entity.User;

import java.util.Map;
import java.util.Vector;

public class MakeResultService {

    private HorseRaceDAO<User> userHorseRaceDAO = new UserDaoImpl();
    private HorseRaceService<Bet> betService = new BetServiceImpl();
    private HorseRaceService<Race> raceService = new RaceServiceImpl();

    public Vector<User> getWinners(int idRace, int idHorse){
        Map<Integer, Bet> idUserMap = betService.getObjectsByTwoParameters(idRace, idHorse);
        Vector<User> userVector = new Vector<>();
        System.out.println(idUserMap.keySet());
        for(Integer idUser: idUserMap.keySet()){
            userVector.add(userHorseRaceDAO.getObject(idUser));
        }
        return userVector;
    }

    public void updateWinnersBalance(int idRace, int idHorse){
        Map<Integer, Bet> idUserMap = betService.getObjectsByTwoParameters(idRace, idHorse);
        Vector<User> userVector = getWinners(idRace, idHorse);
        for (User user: userVector){
            System.out.println(user.getUsername());
            float win = user.getBalance() + idUserMap.get(user.getId()).getPrice();
            System.out.println(win);
            String new_balance = Float.toString(win);
            String [] params = new String[]{new_balance};
            userHorseRaceDAO.updateObject(user.getId(), params);
        }
        raceService.deleteObject(idRace);

    }

}
