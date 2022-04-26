package unicyb.horseracingservice.service;

import java.util.Vector;

public interface HorseRaceService<T> {
    public Vector<T> findAll();
    public T getObject(int ID);
    public String addObject(T object);
    public String deleteObject(int ID);
    public String updateObject(int ID, String[] params);
}
