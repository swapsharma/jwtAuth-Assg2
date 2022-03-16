package com.springsecurity.jwt.model;

import lombok.Data;

@Data
public class JwtResponse {

    private String employeeId;
    private String jwtToken;

    public JwtResponse(String jwtToken, String employeeId) {
        this.jwtToken = jwtToken;
        this.employeeId = employeeId;
    }
}
