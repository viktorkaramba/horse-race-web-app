package unicyb.horseracingservice.service;

import unicyb.horseracingservice.entity.Race;
import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.database.dao.RaceDAOImpl;

import java.util.Vector;

public class RaceServiceImpl implements HorseRaceService<Race>{

    private HorseRaceDAO<Race> RaceDAO = new RaceDAOImpl();

    @Override
    public Vector<Race> findAll() {
        Vector<Race> raceVector = new Vector<>();
        try {
            raceVector = RaceDAO.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return raceVector;
    }

    @Override
    public Race getObject(int ID) {
        Race race = new Race();
        try {
            race = RaceDAO.getObject(ID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return race;
    }

    @Override
    public String addObject(Race object) {
        return null;
    }

    @Override
    public String deleteObject(int ID) {
        return null;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }
}
