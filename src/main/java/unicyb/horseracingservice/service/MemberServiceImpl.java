package unicyb.horseracingservice.service;

import unicyb.horseracingservice.database.dao.HorseRaceDAO;
import unicyb.horseracingservice.database.dao.MemberDAOImpl;
import unicyb.horseracingservice.entity.Member;
import unicyb.horseracingservice.entity.Race;

import java.util.Vector;

public class MemberServiceImpl implements HorseRaceService<Member>{

    private HorseRaceDAO<Member> memberDAO = new MemberDAOImpl();

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
}
