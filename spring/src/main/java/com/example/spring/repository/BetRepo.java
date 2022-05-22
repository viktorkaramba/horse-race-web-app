package com.example.spring.repository;

import com.example.spring.entity.Bets;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetRepo extends CrudRepository<Bets, Integer> {
    Iterable<Bets> findByIDRAAndIDHO(int ID_RA, int ID_HO);
    Bets findByIDUSAndIDRA(int IDUS, int IDRA);
}
