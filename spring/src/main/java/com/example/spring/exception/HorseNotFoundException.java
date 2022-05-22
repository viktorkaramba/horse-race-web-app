package com.example.spring.exception;

public class HorseNotFoundException extends Exception{
    public HorseNotFoundException(String message) {
        super(message);
    }
}
