package unicyb.horseracingservice.database.dao;

import unicyb.horseracingservice.database.DatabaseConnection;
import unicyb.horseracingservice.database.SQLQuery;
import unicyb.horseracingservice.entity.Member;

import java.sql.*;
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
                int idHorse = resultSet.getInt(2);
                int idRace = resultSet.getInt(3);
                Member member = new Member(id, idHorse, idRace);
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
                int idHorse = resultSet.getInt(2);
                int idRace = resultSet.getInt(3);
                member = new Member(id, idHorse, idRace);
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
            statement.setInt(2, object.getIdHorse());
            statement.setInt(3, object.getIdRace());
            statement.executeQuery();
            result = "Member successfully added!!!";
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
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
            statement.executeQuery();
            result = "Member successfully deleted!!!";
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            result = "Error!!! member don't delete";
        }
        return result;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }

    public Member getMemberByRace(int IDRace){
        Member member = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = con.prepareStatement(SQLQuery.SQL_SELECT_MEMBERS_BY_RACE);
            statement.setInt(1, IDRace);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int idHorse = resultSet.getInt(2);
                int idRace = resultSet.getInt(3);
                member = new Member(id, idHorse, idRace);
            }
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return member;

    }
}
