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
        return null;
    }

    @Override
    public String deleteObject(int ID) {
        return null;
    }

    @Override
    public String updateObject(int ID, String[] params) {
        return null;
    }
}
