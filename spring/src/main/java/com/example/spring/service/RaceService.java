package com.example.spring.service;

import com.example.spring.entity.Races;
import com.example.spring.exception.RaceNotFoundException;
import com.example.spring.repository.RaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceService {

    @Autowired
    private RaceRepo raceRepo;

    public Races addRace(Races race){
        return raceRepo.save(race);
    }

    public Iterable<Races> getRaces(){
        return raceRepo.findAll();
    }

    public Races getRace(int id) throws RaceNotFoundException {
        Races race = raceRepo.findById(id).get();
        if (race == null){
            throw new RaceNotFoundException("Race not found");
        }
        return race;
    }

    public void updateISOVER(int ID, boolean isOver){
        Races race = raceRepo.findById(ID).get();
        race.setISOVER(isOver);
        raceRepo.save(race);
    }
}
