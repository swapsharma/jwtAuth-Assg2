package com.springsecurity.jwt.repository;


import com.springsecurity.jwt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    public Employee findByEmployeeName(String employeeName);
}
