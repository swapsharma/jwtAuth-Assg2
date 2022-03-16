package com.springsecurity.jwt.service;

import com.springsecurity.jwt.entity.Employee;
import com.springsecurity.jwt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    public Employee getUserById(String employeeId) {
        return employeeRepository.getById(employeeId);
    }

    public Employee createNewUser(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateUser(Employee updatedUser) {
        String employeeId = updatedUser.getEmployeeId();
        Employee employee = employeeRepository.getById(employeeId);
        employee.setEmployeeName(updatedUser.getEmployeeName());
        employee.setFirstName(updatedUser.getFirstName());
        employee.setPassword(updatedUser.getPassword());
        employee.setDesignation(updatedUser.getDesignation());
        return employee;
    }

    public String deleteUser(String employeeId) {

        Employee employee = employeeRepository.getById(employeeId);
        employeeRepository.delete(employee);
        return "User has been removed!";
    }

    @Override
    public UserDetails loadUserByUsername(String employeeName) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmployeeName(employeeName);

        if (employee != null) {
            return new User(
                    employee.getEmployeeName(),
                    employee.getPassword(),
                    new ArrayList<>()
                    );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + employeeName);
        }
    }
}
