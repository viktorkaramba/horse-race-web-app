package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.Race;

import java.sql.*;
import java.util.Vector;

public class RaceDAOImpl implements HorseRaceDAO<Race>{

    @Override
    public Vector<Race> findAll() {
        Vector<Race> raceVector = new Vector<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_ALL_RACES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String place = resultSet.getString(3);
                Timestamp date = resultSet.getTimestamp(4);
                Float prize = resultSet.getFloat(5);
                Race race = new Race(id, name, place, date, prize);
                raceVector.add(race);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return raceVector;
    }

    @Override
    public Race getObject(int ID) {
        Race race = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_RACES_BY_ID);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String place = resultSet.getString(3);
                Timestamp date = resultSet.getTimestamp(4);
                Float prize = resultSet.getFloat(5);
                race = new Race(id, name, place, date, prize);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return race;
    }

    @Override
    public String addObject(Race object) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_INSERT_RACE);
            statement.setInt(1, object.getId());
            statement.setString(2, object.getName());
            statement.setString(3, object.getPlace());
            statement.setTimestamp(4, object.getDate());
            statement.setFloat(5, object.getPrize());
            statement.executeQuery();
            result = "Race successfully added!!!";
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            result = "Error!!! race don't add";
        }
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_DELETE_RACE);
            statement.setInt(1, ID);
            statement.executeQuery();
            result = "Race successfully deleted!!!";
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            result = "Error!!! race don't delete";
        }
        return result;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }
}
