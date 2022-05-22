package com.example.spring.repository;

import com.example.spring.entity.Bets;
import com.example.spring.entity.Coefficients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoefficientRepo extends CrudRepository<Coefficients, Integer> {
    Coefficients findByIDRAAndIDHO(int ID_RA, int ID_HO);
}
