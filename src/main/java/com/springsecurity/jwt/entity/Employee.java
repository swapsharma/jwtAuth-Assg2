package com.springsecurity.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    private String employeeId;
    private String employeeName;
    private String firstName;
    private String lastName;
    private String password;
    private String designation;

    public Employee(String employeeId, String employeeName, String firstName, String lastName, String password, String designation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.designation = designation;
    }
}
