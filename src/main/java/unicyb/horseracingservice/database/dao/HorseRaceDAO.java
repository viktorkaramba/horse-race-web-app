package unicyb.horseracingservice.database.dao;


import java.util.Map;
import java.util.Vector;

public interface HorseRaceDAO<T> {
    public Vector<T> findAll();
    public T getObject(int ID);
    public String addObject(T object);
    public String deleteObject(int ID);
    public String updateObject(int ID, String[] params);
    public Vector<Integer> getObjectsByParameter(int ID);
    public Vector<T> getObjectsByParameter(Vector<Integer> idVector);
    public Map<Integer, T> getObjectsByTwoParameters(int ID_1, int ID_2);
}
