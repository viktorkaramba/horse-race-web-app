package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.entity.Coefficient;

import java.util.Map;
import java.util.Vector;

public class CoefficientDAOImpl implements HorseRaceDAO<Coefficient> {
    @Override
    public Vector<Coefficient> findAll() {
        return null;
    }

    @Override
    public Coefficient getObject(int ID) {
        return null;
    }

    @Override
    public String addObject(Coefficient object) {
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

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        return null;
    }

    @Override
    public Vector<Coefficient> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }

    @Override
    public Map<Integer, Coefficient> getObjectsByTwoParameters(int ID_1, int ID_2) {
        return null;
    }
}
