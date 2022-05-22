package com.example.spring.service;

import com.example.spring.entity.Coefficients;
import com.example.spring.exception.CoefficientNotFoundException;
import com.example.spring.repository.CoefficientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoefficientService {

    @Autowired
    private CoefficientRepo coefficientRepo;

    public Coefficients addCoefficient(Coefficients coefficients){
        return coefficientRepo.save(coefficients);
    }

    public Iterable<Coefficients> getCoefficients(){
        return coefficientRepo.findAll();
    }

    public Coefficients getCoefficient(int id) throws CoefficientNotFoundException {
        Coefficients coefficient = coefficientRepo.findById(id).get();
        if(coefficient == null){
            throw new CoefficientNotFoundException("Coefficient not found");
        }
        return coefficient;
    }
}
