package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.Coefficient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//Implementation HorseRaceDAO interface for coefficients
public class CoefficientDAOImpl implements HorseRaceDAO<Coefficient> {
    @Override
    public Vector<Coefficient> findAll() {
        Vector<Coefficient> coefficientVector = new Vector<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_ALL_COEFFICIENTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int idRace = resultSet.getInt(2);
                int idHorse = resultSet.getInt(3);
                float value = resultSet.getFloat(4);
                Coefficient coefficient = new Coefficient(id, idRace, idHorse, value);
                coefficientVector.add(coefficient);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return coefficientVector;
    }

    @Override
    public Coefficient getObject(int ID) {
        return null;
    }

    @Override
    public String addObject(Coefficient object){
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_INSERT_COEFFICIENT);
            statement.setInt(1, object.getIdRace());
            statement.setInt(2, object.getIdHorse());
            statement.setFloat(3, object.getValue());
            statement.executeUpdate();
            result = "Coefficient successfully added!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! coefficient don't add";
        }
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
    public Vector<Coefficient> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }

    @Override
    public Coefficient getObjectByParameter(String parameter) {
        return null;
    }

    @Override
    public Map<Integer, Coefficient> getObjectsByTwoParameters(int ID_1, int ID_2) {
        Map<Integer, Coefficient> idCoefficientMap = new HashMap<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_COEFFICIENTS_BY_RACE_HORSE);
            statement.setInt(1, ID_1);
            statement.setInt(2, ID_2);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Coefficient coefficient = getCoefficient(resultSet);
                idCoefficientMap.put(coefficient.getId(), coefficient);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return idCoefficientMap;
    }

    private Coefficient getCoefficient(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        int idRace = resultSet.getInt(2);
        int idHorse = resultSet.getInt(3);
        float value = resultSet.getFloat(4);
        Coefficient coefficient = new Coefficient(id, idRace, idHorse, value);
        return coefficient;
    }
}
