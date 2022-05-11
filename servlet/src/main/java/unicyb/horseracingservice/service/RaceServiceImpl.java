package unicyb.horseracingservice.service;

import unicyb.horseracingservice.entity.Race;
import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.database.dao.RaceDAOImpl;

import java.util.Map;
import java.util.Vector;

public class RaceServiceImpl implements HorseRaceService<Race>{

    private HorseRaceDAO<Race> raceDAO = new RaceDAOImpl();

    @Override
    public Integer getObjectByParameter(int ID) {
        return null;
    }

    @Override
    public Map<Integer, Race> getObjectsByTwoParameters(int ID_1, int ID_2) {
        return null;
    }

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        return null;
    }

    @Override
    public Vector<Race> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }

    @Override
    public Vector<Race> findAll() {
        Vector<Race> raceVector = new Vector<>();
        try {
            raceVector = raceDAO.findAll();
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
            race = raceDAO.getObject(ID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return race;
    }

    @Override
    public String addObject(Race object) {
        String result = raceDAO.addObject(object);
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = raceDAO.deleteObject(ID);
        return result;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        String result = raceDAO.updateObject(ID, params);
        return result;
    }
}
