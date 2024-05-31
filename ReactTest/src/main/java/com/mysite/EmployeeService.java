package com.mysite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getAllEmployees(){
		return employeeMapper.getAllEmployees();
	}
	
	public void insertEmployee(Employee emp) {
		employeeMapper.insertEmployee(emp);
	}
	
	public void deleteEmployee(int id) {
		employeeMapper.deleteEmployee(id);
	}
	
	public void updateEmployee(Employee emp) {
		employeeMapper.updateEmployee(emp);
	}
}
