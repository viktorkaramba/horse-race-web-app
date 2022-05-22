package com.example.spring.service;

import com.example.spring.entity.Bets;
import com.example.spring.exception.BetNotFoundException;
import com.example.spring.repository.BetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetService {

    @Autowired
    private BetRepo betRepo;

    public Bets addBet(Bets bet){
        return betRepo.save(bet);
    }

    public Iterable<Bets> getBets(){
        return betRepo.findAll();
    }

    public Bets getBet(int id) throws BetNotFoundException {
        Bets bet = betRepo.findById(id).get();
        if(bet == null){
            throw new BetNotFoundException("Bet not found");
        }
        return bet;
    }

    public Iterable<Bets> getWinners(int IDRA, int IDHO){
        return betRepo.findByIDRAAndIDHO(IDRA, IDHO);
    }

    public Bets getByUser(int IDUS, int IDRA){
        return betRepo.findByIDUSAndIDRA(IDUS, IDRA);
    }
}
