package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.HorseDAOImpl;
import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.entity.Horse;

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
