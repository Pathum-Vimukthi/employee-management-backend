package com.example.employee_management.exception.customs;

public class InvalidPassword extends RuntimeException{
    public InvalidPassword(String message) {
        super(message);
    }
}
