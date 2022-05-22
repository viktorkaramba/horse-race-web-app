package com.example.spring.exception;

public class MemberNotFoundException extends Exception{
    public MemberNotFoundException(String message) {
        super(message);
    }
}
