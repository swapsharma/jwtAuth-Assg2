package com.springsecurity.jwt.model;

import lombok.Data;

@Data
public class JwtRequest {
    private String employeeName;
    private String employeePassword;
}