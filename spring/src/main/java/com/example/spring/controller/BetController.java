package com.example.spring.controller;

import com.example.spring.entity.Bets;
import com.example.spring.exception.BetNotFoundException;
import com.example.spring.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @GetMapping
    public ResponseEntity getBet(@RequestParam int id){
        try {
            return ResponseEntity.ok(betService.getBet(id));
        }
        catch (BetNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getHorses(){
        try {
            return ResponseEntity.ok(betService.getBets());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/b2")
    public ResponseEntity getWinners(@RequestParam int IDRA, @RequestParam int IDHO){
        try {
            return ResponseEntity.ok(betService.getWinners(IDRA, IDHO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
