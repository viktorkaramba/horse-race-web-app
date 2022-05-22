package com.example.spring.controller;

import com.example.spring.entity.ResponseResult;
import com.example.spring.exception.RaceNotFoundException;
import com.example.spring.service.MakeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/make-result")
public class MakeResultController {

    @Autowired
    private MakeResultService makeResultService;

    @PostMapping
    public ResponseEntity makeResult(@RequestBody ResponseResult responseResult) throws RaceNotFoundException {
        try {
            makeResultService.updateWinnerBalance(responseResult.getIdFirst(), responseResult.getIdSecond());
            return ResponseEntity.ok("Result successfully made");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Make result error");
        }

    }
}
