package com.example.employee_management.controller;

import com.example.employee_management.dto.EmployeeCreateReqDTO;
import com.example.employee_management.dto.EmployeeResDTO;
import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResDTO>> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResDTO> getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResDTO> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeCreateReqDTO employeeCreateReqDTO){
        return employeeService.updateEmployee(employeeId, employeeCreateReqDTO);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }
}
