package com.example.spring.controller;

import com.example.spring.entity.Races;
import com.example.spring.exception.RaceNotFoundException;
import com.example.spring.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

    @PostMapping
    public ResponseEntity addRace(@RequestBody Races race){
        try {
            raceService.addRace(race);
            return ResponseEntity.ok("Race successfully added!!!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Race add error");
        }
    }

    @GetMapping
    public ResponseEntity getRaces(){
        try {
            return ResponseEntity.ok(raceService.getRaces());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
