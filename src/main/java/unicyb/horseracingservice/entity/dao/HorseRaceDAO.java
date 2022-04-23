package unicyb.horseracingservice.entity.dao;


import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.entity.Express;
import unicyb.horseracingservice.entity.Horse;
import unicyb.horseracingservice.entity.Race;

import java.util.Vector;

public interface HorseRaceDAO {
    public Vector<Race> findAllRaces();
    public Vector<Bet> findAllBets();
    public Vector<Horse> findAllHorses();
    public Vector<Express> findAllExpress();

}
