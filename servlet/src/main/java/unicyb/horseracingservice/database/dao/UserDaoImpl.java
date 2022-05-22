package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.User;

import java.sql.*;
import java.util.Map;
import java.util.Vector;

//Implementation HorseRaceDAO interface for users
public class UserDaoImpl implements HorseRaceDAO<User>{


    @Override
    public Vector<User> findAll() {
        Vector<User> userVector = new Vector<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Float balance = resultSet.getFloat(3);
                User user = new User(id, name, balance);
                userVector.add(user);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
        }
        return userVector;
    }

    @Override
    public User getObject(int ID) {
        User user = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_USER_BY_ID);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Float balance= resultSet.getFloat(3);
                user = new User(id, name, balance);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
        }
        return user;
    }

    @Override
    public String addObject(User object) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_INSERT_USER);
            statement.setInt(1, object.getID());
            statement.setString(2, object.getUsername());
            statement.setFloat(3, object.getBalance());
            statement.executeUpdate();
            result = "User successfully added!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! user don't add";
        }
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_DELETE_USER);
            statement.setInt(1, ID);
            statement.executeUpdate();
            result = "User successfully deleted!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! user don't delete";
        }
        return result;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_UPDATE_USER);
            Float new_balance = Float.parseFloat(params[0]);
            statement.setFloat(1, new_balance);
            statement.setInt(2, ID);
            statement.executeUpdate();
            result = "User successfully updated!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! user don't updated";
        }
        return result;
    }

    @Override
    public  User getObjectByParameter(String parameter) {
        User user = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_USER_BY_USERNAME);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Float balance= resultSet.getFloat(3);
                user = new User(id, name, balance);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
        }
        return user;
    }

    @Override
    public Vector<User> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        return null;
    }

    @Override
    public Map<Integer, User> getObjectsByTwoParameters(int ID_1, int ID_2) {
        return null;
    }
}
