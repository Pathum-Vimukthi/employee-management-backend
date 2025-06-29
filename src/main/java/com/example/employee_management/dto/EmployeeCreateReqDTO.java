package com.example.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateReqDTO {
    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phone;
}
