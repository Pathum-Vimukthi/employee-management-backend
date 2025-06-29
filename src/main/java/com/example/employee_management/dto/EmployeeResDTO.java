package com.example.employee_management.dto;

import com.example.employee_management.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResDTO {
    private Long employee_id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    public Employee toEntity() {
        Employee employee = new Employee();

        employee.setEmployeeId(this.employee_id);
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setEmail(this.email);
        employee.setPhone(this.phone);

        return employee;
    }
}
