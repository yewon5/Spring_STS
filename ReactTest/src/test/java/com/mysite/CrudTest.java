package com.mysite;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CrudTest {
	@Autowired
	private EmployeeMapper employeeMaper;
	
	
	@Test
	public void selectTest() {
		assertNotNull(employeeMaper.getAllEmployees());
	}
}
