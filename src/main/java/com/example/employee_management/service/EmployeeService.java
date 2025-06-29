package com.example.employee_management.service;

import com.example.employee_management.dto.EmployeeCreateReqDTO;
import com.example.employee_management.dto.EmployeeResDTO;
import com.example.employee_management.exception.customs.UserNotFound;
import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<List<EmployeeResDTO>> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResDTO> employeeResDTOList = new ArrayList<>();

        for(Employee employee : employeeList) {
            EmployeeResDTO employeeResDTO = employee.toEmployeeResDTO();
            employeeResDTOList.add(employeeResDTO);
        }

        return ResponseEntity.ok(employeeResDTOList);
    }

    public ResponseEntity<EmployeeResDTO> getEmployeeById(Long employeeId) {
        if(employeeId == null) {
            throw new RuntimeException("Employee id is required");
        }else {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

            if(employeeOptional.isPresent()) {
                return ResponseEntity.ok(employeeOptional.get().toEmployeeResDTO());
            }else{
                throw new UserNotFound("Employee not found for given Id");
            }
        }
    }

    public ResponseEntity<EmployeeResDTO> updateEmployee(Long employeeId, EmployeeCreateReqDTO employeeCreateReqDTO) {
        if(employeeId == null) {
            throw new RuntimeException("Employee id is required");
        }else{
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

            if(employeeOptional.isPresent()) {
                Employee employeeUpdate = employeeOptional.get();

                employeeUpdate.setFirstName(employeeCreateReqDTO.getFirstName());
                employeeUpdate.setLastName(employeeCreateReqDTO.getLastName());
                employeeUpdate.setPassword(passwordEncoder.encode(employeeCreateReqDTO.getPassword()));
                employeeUpdate.setEmail(employeeCreateReqDTO.getEmail());
                employeeUpdate.setPhone(employeeCreateReqDTO.getPhone());

                employeeUpdate = employeeRepository.save(employeeUpdate);
                return ResponseEntity.ok(employeeUpdate.toEmployeeResDTO());
            }else{
                throw new UserNotFound("Employee data not found for given Id");
            }
        }
    }

    public ResponseEntity<String> deleteEmployee(Long employeeId) {
        if(employeeId == null) {
            throw new RuntimeException("Employee id is required");
        }else{
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

            if(employeeOptional.isPresent()) {
                employeeRepository.deleteById(employeeId);
                return ResponseEntity.ok("Employee deleted successfully");
            }else{
                throw new UserNotFound("Employee not found for given Id");
            }
        }
    }
}
