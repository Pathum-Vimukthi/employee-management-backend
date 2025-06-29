package com.example.employee_management.exception;

import com.example.employee_management.exception.customs.InvalidPassword;
import com.example.employee_management.exception.customs.UserNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFound ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("path", request.getRequestURI());
        response.put("method", request.getMethod());
        response.put("code", 400);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPassword.class)
    public ResponseEntity<Object> handleInvalidPassword(InvalidPassword ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("path", request.getRequestURI());
        response.put("method", request.getMethod());
        response.put("code", 400);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
