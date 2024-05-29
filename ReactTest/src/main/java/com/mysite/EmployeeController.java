package com.mysite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	public EmployeeService employeeService; 

	@GetMapping("/employee")
	public List<Employee> getAllEmployees(){
		System.out.println("result:" + employeeService.getAllEmployees());
		return employeeService.getAllEmployees();
	}
}
