package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.database.dao.MemberDAOImpl;
import unicyb.horseracingservice.entity.Member;

import java.util.Map;
import java.util.Vector;

//Implementation HorseRaceService interface for member
public class MemberServiceImpl implements HorseRaceService<Member>{

    private HorseRaceDAO<Member> memberDAO = new MemberDAOImpl();

    @Override
    public Integer getObjectByParameter(int ID) {
        return null;
    }

    @Override
    public Map<Integer, Member> getObjectsByTwoParameters(int ID_1, int ID_2) {
        return null;
    }

    @Override
    public Vector<Member> findAll() {
        Vector<Member> memberVector = new Vector<>();
        try {
            memberVector = memberDAO.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return memberVector;
    }

    @Override
    public Member getObject(int ID) {
        Member member = new Member();
        try {
            member = memberDAO.getObject(ID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public String addObject(Member object) {
        String result = memberDAO.addObject(object);
        return result;
    }

    @Override
    public String deleteObject(int ID) {
        String result = memberDAO.deleteObject(ID);
        return result;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }

    @Override
    public Vector<Integer> getObjectsByParameter(int ID) {
        Vector<Integer> idHorseVector = new Vector<>();
        try {
            idHorseVector = memberDAO.getObjectsByParameter(ID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return idHorseVector;
    }

    @Override
    public Vector<Member> getObjectsByParameter(Vector<Integer> idVector) {
        return null;
    }
}
