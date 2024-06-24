package com.mysite;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
		user.setFirstname("one");
		user.setLastname("kim");
		user.setActive(false);
		user.setAge(22);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
		
		user = new UserExam();
		user.setFirstname("two");
		user.setLastname("kim");
		user.setActive(false);
		user.setAge(35);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
		
		user = new UserExam();
		user.setFirstname("three");
		user.setLastname("kim");
		user.setActive(false);
		user.setAge(12);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
		
		user = new UserExam();
		user.setFirstname("four");
		user.setLastname("kim");
		user.setActive(false);
		user.setAge(28);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
		
		user = new UserExam();
		user.setFirstname("five");
		user.setLastname("kim");s
		user.setActive(false);
		user.setAge(38);
		user.setStartDate(currentDateTime);
		userExamRepository.save(user);
	}

	@Test @Disabled
	void test1() {
		List<UserExam> users = userExamRepository.findAll();
		System.out.println("데이터 갯수 : " + users.size());
	}
	
	@Test @Disabled
	void test2() {
		List<UserExam> users = userExamRepository.findByLastnameAndFirstnameOrderByUserIdDesc("kim", "one");
		for(UserExam user : users) {
			System.out.println(user.getLastname() + " : " + user.getFirstname());
		}
	}
	
	@Test @Disabled
	void test3() {
		System.out.println(userExamRepository.countByFirstnameIgnoreCaseLike("one"));
	}
	
	/*
	@Test 
	void test4() {
		System.out.println(userExamRepository.existsByStartDateLessThanEqual(LocalDateTime.now()));
	}
	*/
	

}
