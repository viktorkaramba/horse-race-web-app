package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.HorseDAOImpl;
import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.entity.Horse;

import java.util.Map;
import java.util.Vector;

public class HorseServiceImpl implements HorseRaceService<Horse> {

    private HorseRaceDAO<Horse> horseDAO = new HorseDAOImpl();

    @Override
    public Vector<Horse> findAll() {
        Vector<Horse> horseVector = new Vector<>();
        try {
            horseVector = horseDAO.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return horseVector;
    }

    @Override
    public Horse getObject(int ID) {
        Horse horse = new Horse();
        try {
            horse = horseDAO.getObject(ID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return horse;
    }

    @Override
    public String addObject(Horse object) {
        String result = horseDAO.addObject(object);
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = horseDAO.deleteObject(ID);
        return result;
    }

    @Override
    public Vector<Horse> getObjectsByParameter(Vector<Integer> idVector) {
        Vector<Horse> horseVector = new Vector<>();
        try {
            horseVector = horseDAO.getObjectsByParameter(idVector);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return horseVector;
    }

    @Override
    public Integer getObjectByParameter(int ID) {
        return null;
    }

    @Override
    public Map<Integer,Horse> getObjectsByTwoParameters(int ID_1, int ID_2) {
        return null;
    }

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        return null;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }

}
