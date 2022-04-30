package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.BetDAOImpl;
import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.entity.Horse;

import java.util.Vector;

public class BetServiceImpl implements HorseRaceService<Bet>{

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
}
