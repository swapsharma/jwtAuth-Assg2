package com.springsecurity.jwt.controller;

import com.springsecurity.jwt.model.JwtRequest;
import com.springsecurity.jwt.model.JwtResponse;
import com.springsecurity.jwt.repository.EmployeeRepository;
import com.springsecurity.jwt.service.EmployeeService;
import com.springsecurity.jwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        log.info(">>> createToken() - Authentication started");
        String employeeName = jwtRequest.getEmployeeName();
        String employeePassword = jwtRequest.getEmployeePassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employeeName, employeePassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userDetails = employeeService.loadUserByUsername(employeeName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        String employeeId = employeeRepository.findByEmployeeName(employeeName).getEmployeeId();
        log.info("<<< createToken() - Authentication ended");
        return ResponseEntity.ok(new JwtResponse(newGeneratedToken, employeeId));
    }
}