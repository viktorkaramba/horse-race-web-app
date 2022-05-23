package com.example.spring.controller;

import com.example.spring.entity.Bets;
import com.example.spring.exception.BetNotFoundException;
import com.example.spring.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/bets")
public class BetController {

    @Autowired
    private BetService betService;

    @PostMapping
    public ResponseEntity addBet(@RequestBody Bets bet){
        try {
            betService.addBet(bet);
            return ResponseEntity.ok("Bet successfully added!!!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Bet add error");
        }
    }

    @GetMapping()
    public ResponseEntity getBets(){
        try {
            return ResponseEntity.ok(betService.getBets());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
