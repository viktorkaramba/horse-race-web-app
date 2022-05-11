package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.Member;

import java.sql.*;
import java.util.Map;
import java.util.Vector;

public class MemberDAOImpl implements HorseRaceDAO<Member>{

    @Override
    public Vector<Member> findAll() {
        Vector<Member> memberVector = new Vector<>();
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_ALL_MEMBERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int idRace = resultSet.getInt(2);
                int idHorse = resultSet.getInt(3);
                Member member = new Member(id, idRace, idHorse);
                memberVector.add(member);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return memberVector;
    }

    @Override
    public Member getObject(int ID) {
        Member member = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_MEMBERS_BY_ID);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int idRace = resultSet.getInt(2);
                int idHorse = resultSet.getInt(3);
                member = new Member(id, idRace, idHorse);
                con.close();
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public String addObject(Member object) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_INSERT_MEMBER);
            statement.setInt(1, object.getId());
            statement.setInt(2, object.getIdRace());
            statement.setInt(3, object.getIdHorse());
            statement.executeUpdate();
            result = "Member successfully added!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! member don't add";
        }
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_DELETE_MEMBER);
            statement.setInt(1, ID);
            statement.executeUpdate();
            result = "Member successfully deleted!!!";
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
            result = "Error!!! member don't delete";
        }
        return result;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }

    @Override
    public Member getObjectByParameter(String parameter) {
        return null;
    }

    @Override
    public Map<Integer, Member> getObjectsByTwoParameters(int ID_1, int ID_2) {
        return null;
    }

    @Override
    public Vector<Member> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        Vector<Integer> idHorseVector = new Vector<>();;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_MEMBERS_BY_RACE);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int idHorse = resultSet.getInt(3);
                idHorseVector.add(idHorse);
            }
            con.close();
        }
        catch (SQLException | ClassNotFoundException e){
        }
        return idHorseVector;
    }
}
