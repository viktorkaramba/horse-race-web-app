package com.example.spring.service;

import com.example.spring.entity.Horses;
import com.example.spring.exception.HorseNotFoundException;
import com.example.spring.repository.HorseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorseService {

    @Autowired
    private HorseRepo horseRepo;

    public Horses addHorse(Horses horse){
        return horseRepo.save(horse);
    }

    public Iterable<Horses> getHorses(){
        return horseRepo.findAll();
    }

    public Horses getHorse(int id) throws HorseNotFoundException {
        Horses horse = horseRepo.findById(id).get();
        if(horse == null){
            throw new HorseNotFoundException("Horse not found");
        }
        return horse;
    }

    public Iterable<Horses> getHorsesById(Iterable<Integer> idHorses){
        return horseRepo.findAllById(idHorses);
    }
}
