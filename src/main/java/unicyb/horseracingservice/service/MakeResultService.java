package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.database.dao.UserDaoImpl;
import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.entity.User;

import java.util.Map;
import java.util.Vector;

public class MakeResultService {

    private HorseRaceDAO<User> userHorseRaceDAO = new UserDaoImpl();
    private HorseRaceService<Bet> betService = new BetServiceImpl();

    public Vector<User> getWinners(int idRace, int idHorse){
        Map<Integer, Bet> idUserMap = betService.getObjectsByTwoParameters(idRace, idHorse);
        Vector<User> userVector = new Vector<>();
        for(int idUser: idUserMap.keySet()){
            userVector.add(userHorseRaceDAO.getObject(idUser));
        }
        return userVector;
    }

    public void updateWinnersBalance(int idRace, int idHorse){
        Map<Integer, Bet> idUserMap = betService.getObjectsByTwoParameters(idRace, idHorse);
        Vector<User> userVector = getWinners(idRace, idHorse);
        for (int i = 0, y = 0; i < userVector.size() && y < idUserMap.values().size(); i++, y++){
            User user = userVector.get(i);
            float win = user.getBalance() + idUserMap.get(y).getPrice();
            String new_balance = Float.toString(win);
            String [] params = new String[]{new_balance};
            userHorseRaceDAO.updateObject(user.getId(), params);
        }
    }

}
