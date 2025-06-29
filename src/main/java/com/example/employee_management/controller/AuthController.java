package com.example.employee_management.controller;

import com.example.employee_management.dto.EmployeeCreateReqDTO;
import com.example.employee_management.dto.EmployeeResDTO;
import com.example.employee_management.dto.LoginRequestDTO;
import com.example.employee_management.model.Employee;
import com.example.employee_management.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<EmployeeResDTO> register(@RequestBody EmployeeCreateReqDTO employeeCreateReqDTO) {

        return new ResponseEntity<>(
                authService.register(employeeCreateReqDTO), HttpStatus.CREATED
        );

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String response = authService.login(loginRequestDTO);
        return new ResponseEntity<>(
                response,HttpStatus.OK);
    }
}
