package com.springsecurity.jwt.controller;

import com.springsecurity.jwt.entity.Employee;
import com.springsecurity.jwt.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/userList")
    public ResponseEntity<List<Employee>> getAllUsers() {
        List<Employee> userList = employeeService.getAllUsers();
        if(userList!=null) {
            return ResponseEntity.ok(userList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getUserById(@PathVariable(name = "employeeId") String employeeId) {
        log.info(">>> getUserById() - Get User started");
        Employee employee = employeeService.getUserById(employeeId);
        if(employee!=null) {
            return ResponseEntity.ok(employee);
        }
        log.info("<<< getUserById() - Get User ended");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/createUser")
    public ResponseEntity<Employee> createUser(@RequestBody Employee user) {
        Employee newUser = employeeService.createNewUser(user);
        if(newUser!=null) {
            return ResponseEntity.ok(newUser);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PatchMapping("/updateUser")
    public ResponseEntity<Employee> updateUser(@RequestBody Employee updatedUser) {
        Employee user = employeeService.updateUser(updatedUser);
        if(user!=null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") String userId) {
        String message = employeeService.deleteUser(userId);
        if(message!=null) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}