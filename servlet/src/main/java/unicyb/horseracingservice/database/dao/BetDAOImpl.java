package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.Bet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BetDAOImpl implements HorseRaceDAO<Bet> {

    @Override
    public Vector<Bet> findAll() {
        Vector<Bet> betVector = new Vector<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_ALL_BETS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Bet bet = getBet(resultSet);
                betVector.add(bet);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return betVector;
    }

    @Override
    public Bet getObject(int ID) {
        Bet bet = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_BETS_BY_ID);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                bet = getBet(resultSet);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return bet;
    }

    private Bet getBet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        int idUser = resultSet.getInt(2);
        int idRace = resultSet.getInt(3);
        int idHorse = resultSet.getInt(4);
        float price = resultSet.getFloat(5);
        int top = resultSet.getInt(6);
        Bet bet = new Bet(id, idUser, idRace, idHorse, price, top);
        return bet;
    }

    @Override
    public String addObject(Bet object) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_INSERT_BETS);
            statement.setInt(1, object.getIdUser());
            statement.setInt(2, object.getIdRace());
            statement.setInt(3, object.getIdHorse());
            statement.setFloat(4, object.getPrice());
            statement.setInt(5, object.getTop());
            statement.executeUpdate();
            result = "Bet successfully added!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! bet don't add";
        }
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_DELETE_BETS);
            statement.setInt(1, ID);
            statement.executeUpdate();
            result = "Bet successfully deleted!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! bet don't delete";
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
    public Map<Integer, Bet> getObjectsByTwoParameters(int ID_1, int ID_2) {
        Map<Integer, Bet> idUserMap = new HashMap<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_WIN_BETS_BY_RACE_HORSE);
            statement.setInt(1, ID_1);
            statement.setInt(2, ID_2);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Bet bet = getBet(resultSet);
                idUserMap.put(bet.getIdUser(), bet);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return idUserMap;
    }

    @Override
    public Vector<Bet> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        return null;
    }

    @Override
    public Bet getObjectByParameter(String parameter) {
        return null;
    }
}
