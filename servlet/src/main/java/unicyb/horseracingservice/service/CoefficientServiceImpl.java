package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.CoefficientDAOImpl;
import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.entity.Coefficient;
import unicyb.horseracingservice.entity.Horse;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CoefficientServiceImpl implements HorseRaceService<Coefficient> {

    private HorseRaceDAO<Coefficient> coefficientDAO = new CoefficientDAOImpl();
    @Override
    public Vector<Coefficient> findAll() {
        Vector<Coefficient> coefficientVector = new Vector<>();
        try {
            coefficientVector = coefficientDAO.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return coefficientVector;
    }

    @Override
    public Coefficient getObject(int ID) {
        return null;
    }

    @Override
    public String addObject(Coefficient object) {
        String result = coefficientDAO.addObject(object);
        return result;
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
    public Integer getObjectByParameter(int ID) {
        return null;
    }

    @Override
    public Vector<Coefficient> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }

    @Override
    public Map<Integer, Coefficient> getObjectsByTwoParameters(int ID_1, int ID_2) {
        Map<Integer, Coefficient> idCoefficientMap = new HashMap<>();
        try {
            idCoefficientMap = coefficientDAO.getObjectsByTwoParameters(ID_1, ID_2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return idCoefficientMap;
    }
}
