package unicyb.horseracingservice.entity.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.Bet;
import unicyb.horseracingservice.entity.Express;
import unicyb.horseracingservice.entity.Horse;
import unicyb.horseracingservice.entity.Race;

import java.sql.*;
import java.util.Vector;

public class HorseRaceDAOImpl implements HorseRaceDAO{

    @Override
    public Vector<Race> findAllRaces() {
        Vector<Race> raceVector = new Vector<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_ALL_RACES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String place = resultSet.getString(2);
                Timestamp date = resultSet.getTimestamp(3);
                Float prize = resultSet.getFloat(4);
                Race race = new Race(id, place, date, prize);
                raceVector.add(race);
            }
        }
        catch (SQLException | ClassNotFoundException e){
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
