package com.example.spring.service;

import com.example.spring.entity.Bets;
import com.example.spring.entity.Users;
import com.example.spring.exception.RaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MakeResultService {

    @Autowired
    private UserService userService;
    @Autowired
    private BetService betService;
    @Autowired
    private RaceService raceService;

    public List<Users> getWinners(int IDRA, int IDHO) throws RaceNotFoundException {
        Iterable<Bets> winBets = betService.getWinners(IDRA, IDHO);
        List<Users> users = new ArrayList<>();
        for(Bets bet: winBets){
            users.add(userService.getUser((long) bet.getIDUS()));
        }
        return users;
    }

    public void updateWinnerBalance(int IDRA, int IDHO) throws RaceNotFoundException {
        List<Users> users = getWinners(IDRA, IDHO);
        for (Users user: users){
            Bets bet = betService.getByUser(user.getID().intValue(), IDRA);
            float win = user.getBalance() + bet.getPrice();
            System.out.println(win);
            userService.updateBalance(user.getID(), win);
        }
        raceService.updateISOVER(IDRA, true);
    }

}
