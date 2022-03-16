package com.springsecurity.jwt;

import com.springsecurity.jwt.entity.Employee;
import com.springsecurity.jwt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtAuthApplication implements CommandLineRunner {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Employee employee1 = new Employee("EMP0001", "swapsharma", "Swapnil", "Sharma", passwordEncoder.encode("qwerty1"), "SE-II");
		Employee employee2 = new Employee("EMP0002", "jamparekh", "Jamit", "Parekh", passwordEncoder.encode("qwerty12"), "SDET");
		Employee employee3 = new Employee("EMP0003", "virkoh", "Virat", "Kohi", passwordEncoder.encode("qwerty13"), "QA");
		Employee employee4 = new Employee("EMP0004", "rohsharma", "Rohit", "Sharma", passwordEncoder.encode("qwerty14"), "Tech Lead");
		Employee employee5 = new Employee("EMP0005", "jasboom", "Jaspreet", "Bumrah", passwordEncoder.encode("qwerty15"), "Project Manager");

		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
		employeeRepository.save(employee4);
		employeeRepository.save(employee5);
	}
}
