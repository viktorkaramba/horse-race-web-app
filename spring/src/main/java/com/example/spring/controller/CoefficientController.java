package com.example.spring.controller;

import com.example.spring.entity.Coefficients;
import com.example.spring.entity.ResponseResult;
import com.example.spring.exception.CoefficientNotFoundException;
import com.example.spring.service.CoefficientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/coefficients")
public class CoefficientController {

    @Autowired
    private CoefficientService coefficientService;

    @PostMapping
    public ResponseEntity addCoefficient(@RequestBody Coefficients coefficients){
        try {
            coefficientService.addCoefficient(coefficients);
            return ResponseEntity.ok("Coefficient successfully added!!!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Coefficient add error");
        }
    }

    @GetMapping
    public ResponseEntity getHorses(){
        try {
            return ResponseEntity.ok(coefficientService.getCoefficients());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
    
    @PutMapping
    public ResponseEntity getByIDRAIDHO(@RequestBody ResponseResult responseResult){
        try {
            return ResponseEntity.ok(coefficientService.getByIDRAAndIDHO(responseResult.getIdFirst(),
                                                                         responseResult.getIdSecond()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Coefficient add error");
        }
    }
}
