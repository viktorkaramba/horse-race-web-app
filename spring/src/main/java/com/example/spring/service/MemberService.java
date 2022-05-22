package com.example.spring.service;

import com.example.spring.entity.Members;
import com.example.spring.entity.Races;
import com.example.spring.exception.MemberNotFoundException;
import com.example.spring.exception.RaceNotFoundException;
import com.example.spring.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;

    public Members addMember(Members member){
        return memberRepo.save(member);
    }

    public Iterable<Members> getMembers(){
        return memberRepo.findAll();
    }

    public Members getMember(int id) throws MemberNotFoundException {
        Members member = memberRepo.findById(id).get();
        if (member == null){
            throw new MemberNotFoundException("Member not found");
        }
        return member;
    }

    public Iterable<Members> getMembersByRace(int id){
        return memberRepo.findByIDRA(id);
    }
}
