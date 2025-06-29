package com.example.employee_management.model;

import com.example.employee_management.dto.EmployeeResDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL" ,unique = true)
    private String email;

    @Column(name = "PHONE")
    private String phone;

    public EmployeeResDTO toEmployeeResDTO() {
        EmployeeResDTO employeeResDTO = new EmployeeResDTO();
        employeeResDTO.setEmployee_id(this.employeeId);
        employeeResDTO.setFirstName(this.firstName);
        employeeResDTO.setLastName(this.lastName);
        employeeResDTO.setEmail(this.email);
        employeeResDTO.setPhone(this.phone);

        return employeeResDTO;
    }
}
