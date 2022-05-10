package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.BetDAOImpl;
import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.entity.Bet;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BetServiceImpl implements HorseRaceService<Bet>{

    @Override
    public Map<Integer, Bet> getObjectsByTwoParameters(int ID_1, int ID_2) {
        Map<Integer, Bet> idUserMap = new HashMap<>();
        try {
            idUserMap = betDAO.getObjectsByTwoParameters(ID_1, ID_2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return idUserMap;
    }

    private HorseRaceDAO<Bet> betDAO = new BetDAOImpl();

    @Override
    public Vector<Bet> findAll() {
        Vector<Bet> betVector = new Vector<>();
        try {
            betVector = betDAO.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return betVector;
    }

    @Override
    public Bet getObject(int ID) {
        Bet bet = new Bet();
        try {
            bet = betDAO.getObject(ID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bet;
    }

    @Override
    public String addObject(Bet object) {
        String result = betDAO.addObject(object);
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = betDAO.deleteObject(ID);
        return result;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        return null;
    }

    @Override
    public Vector<Bet> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }
}
