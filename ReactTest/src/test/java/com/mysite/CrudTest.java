package com.mysite;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CrudTest {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Test
	public void selectTest() {
		assertNotNull(employeeMapper.getAllEmployees());
	}
	
	@Disabled //테스트 중지
	@Test
	public void insertTest() {
		Employee emp = new Employee();
		emp.setEmail("lim@test.com");
		emp.setName("임꺽정");
		
		employeeMapper.insertEmployee(emp);
	}
}
