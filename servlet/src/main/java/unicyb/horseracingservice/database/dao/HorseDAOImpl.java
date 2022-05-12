package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.Horse;

import java.sql.*;
import java.util.Map;
import java.util.Vector;

//Implementation HorseRaceDAO interface for horses
public class HorseDAOImpl implements HorseRaceDAO<Horse> {

    @Override
    public Vector<Horse> findAll() {
        Vector<Horse> horseVector = new Vector<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_ALL_HORSES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int number = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String breed = resultSet.getString(4);
                Horse horse = new Horse(id, number, name, breed);
                horseVector.add(horse);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return horseVector;
    }

    @Override
    public Horse getObject(int ID) {
        Horse horse = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_HORSES_BY_ID);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int number = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String breed = resultSet.getString(4);
                horse = new Horse(id, number, name, breed);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return horse;
    }

    @Override
    public String addObject(Horse object) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_INSERT_HORSE);
            statement.setInt(1, object.getId());
            statement.setInt(2, object.getNumber());
            statement.setString(3, object.getName());
            statement.setString(4, object.getBreed());
            statement.executeUpdate();
            result = "Horse successfully added!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! horse don't add";
        }
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_DELETE_HORSE);
            statement.setInt(1, ID);
            statement.executeUpdate();
            result = "Horse successfully deleted!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! horse don't delete";
        }
        return result;
    }

    @Override
    public Horse getObjectByParameter(String parameter) {
        return null;
    }

    @Override
    public Map<Integer, Horse> getObjectsByTwoParameters(int ID_1, int ID_2) {
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
    public Vector<Horse> getObjectsByParameter(Vector<Integer> idVector) {
        Vector<Horse> horseVector = new Vector<>();
        for (int ID: idVector) {
            try {
                Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_HORSES_BY_ID);
                statement.setInt(1, ID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int number = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    String breed = resultSet.getString(4);
                    Horse horse = new Horse(id, number, name, breed);
                    horseVector.add(horse);
                }
                con.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return horseVector;
    }

}
