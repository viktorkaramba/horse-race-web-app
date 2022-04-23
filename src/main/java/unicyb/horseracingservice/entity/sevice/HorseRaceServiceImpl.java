package unicyb.horseracingservice.entity.sevice;

import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.entity.Express;
import unicyb.horseracingservice.entity.Horse;
import unicyb.horseracingservice.entity.Race;
import unicyb.horseracingservice.entity.dao.HorseRaceDAO;
import unicyb.horseracingservice.entity.dao.HorseRaceDAOImpl;

import java.util.Vector;

public class HorseRaceServiceImpl implements HorseRaceService{

    private HorseRaceDAO horseRaceDAO = new HorseRaceDAOImpl();

    @Override
    public Vector<Race> findAllRaces() {
        Vector<Race> raceVector = new Vector<>();
        try {
            raceVector = horseRaceDAO.findAllRaces();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return raceVector;
    }

    @Override
    public Vector<Bet> findAllBets() {
        return null;
    }

    @Override
    public Vector<Horse> findAllHorses() {
        return null;
    }

    @Override
    public Vector<Express> findAllExpress() {
        return null;
    }
}
