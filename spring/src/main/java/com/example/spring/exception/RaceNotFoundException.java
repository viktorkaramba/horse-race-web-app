package com.example.spring.exception;

public class RaceNotFoundException extends Exception{
    public RaceNotFoundException(String message) {
        super(message);
    }
}
