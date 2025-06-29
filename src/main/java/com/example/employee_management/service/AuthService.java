package com.example.employee_management.service;

import com.example.employee_management.dto.EmployeeCreateReqDTO;
import com.example.employee_management.dto.EmployeeResDTO;
import com.example.employee_management.dto.LoginRequestDTO;
import com.example.employee_management.exception.customs.InvalidPassword;
import com.example.employee_management.exception.customs.UserNotFound;
import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeResDTO register(EmployeeCreateReqDTO employeeCreateReqDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmail(employeeCreateReqDTO.getEmail());

        if (optionalEmployee.isPresent()) {
            throw new RuntimeException("Employee already exists");
        }

        Employee employee = Employee.builder()
                .firstName(employeeCreateReqDTO.getFirstName())
                .lastName(employeeCreateReqDTO.getLastName())
                .email(employeeCreateReqDTO.getEmail())
                .password(passwordEncoder.encode(employeeCreateReqDTO.getPassword()))
                .phone(employeeCreateReqDTO.getPhone())
                .build();

        employeeRepository.save(employee);

        EmployeeResDTO employeeResDTO = EmployeeResDTO.builder()
                .employee_id(employee.getEmployeeId())
                .firstName(employeeCreateReqDTO.getFirstName())
                .lastName(employeeCreateReqDTO.getLastName())
                .email(employeeCreateReqDTO.getEmail())
                .phone(employeeCreateReqDTO.getPhone())
                .build();

        return employeeResDTO;
    }

    public String login(LoginRequestDTO loginRequestDTO) {
        Optional<Employee> employee = employeeRepository.findEmployeeByEmail(loginRequestDTO.getEmail());

        if (employee.isEmpty()) {
            throw new UserNotFound("Employee not found");
        }

        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), employee.get().getPassword())) {
            throw new InvalidPassword("Invalid password");
        }

        return "Employee logged in successfully";
    }
}
