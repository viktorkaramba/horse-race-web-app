package com.example.spring.exception;

public class BetNotFoundException extends Exception{
    public BetNotFoundException(String message) {
        super(message);
    }
}
