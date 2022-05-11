package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.Horse;
import unicyb.horseracingservice.entity.Member;
import unicyb.horseracingservice.entity.Race;

import java.sql.*;
import java.util.Map;
import java.util.Vector;

public class RaceDAOImpl implements HorseRaceDAO<Race>{

    private HorseRaceDAO<Member> memberDAO = new MemberDAOImpl();
    private HorseRaceDAO<Horse> horseDAO = new HorseDAOImpl();

    @Override
    public Race getObjectByParameter(String parameter) {
        return null;
    }

    @Override
    public Map<Integer, Race> getObjectsByTwoParameters(int ID_1, int ID_2) {
        return null;
    }

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
                Vector<Integer> idHorsesVector = memberDAO.getObjectsByParameter(id);
                Vector<Horse> horseVector = horseDAO.getObjectsByParameter(idHorsesVector);
                Race race = new Race(id, name, place, date, prize, horseVector);
                raceVector.add(race);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
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
                Vector<Integer> idHorsesVector = memberDAO.getObjectsByParameter(id);
                Vector<Horse> horseVector = horseDAO.getObjectsByParameter(idHorsesVector);
                race = new Race(id, name, place, date, prize, horseVector);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
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
            statement.executeUpdate();
            result = "Race successfully added!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
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
            statement.executeUpdate();
            result = "Race successfully deleted!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! race don't delete";
        }
        return result;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }

    @Override
    public Vector<Race> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        return null;
    }
}
