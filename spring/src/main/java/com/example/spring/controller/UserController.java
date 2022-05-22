package com.example.spring.controller;

import com.example.spring.entity.ResponseResult;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity updateBalance(@RequestBody ResponseResult responseResult){
        try {
            userService.decreaseUserBalance(responseResult);
            return ResponseEntity.ok("User balance successfully update!!!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("User balance update error");
        }
    }

    @GetMapping
    public ResponseEntity getUsers(){
        try {
            return ResponseEntity.ok(userService.getUsers());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error to get users");
        }
    }

    @GetMapping("/*")
    public ResponseEntity getUser(HttpServletRequest request){
        String test = request.getRequestURI();
        String username = test.replace("/users/", "");
        try {
            return ResponseEntity.ok(userService.getByUsername(username));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
