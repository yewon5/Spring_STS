package com.mysite;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaExam2ApplicationTests {
	@Autowired
	UserExamRepository userExamRepository;
	
	@Test
	void contextLoads() {
	}
	
	@BeforeEach
	void insertTestData() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		UserExam user = new UserExam();
		user.setFirstName("one");
		user.setLastName("kim");
		user.setActive(false);
		user.setAge(22);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
		
		user = new UserExam();
		user.setFirstName("two");
		user.setLastName("kim");
		user.setActive(false);
		user.setAge(35);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
		
		user = new UserExam();
		user.setFirstName("three");
		user.setLastName("kim");
		user.setActive(false);
		user.setAge(12);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
		
		user = new UserExam();
		user.setFirstName("four");
		user.setLastName("kim");
		user.setActive(false);
		user.setAge(28);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
		
		user = new UserExam();
		user.setFirstName("five");
		user.setLastName("kim");
		user.setActive(false);
		user.setAge(38);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
	}

}
