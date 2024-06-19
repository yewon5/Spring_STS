package com.mysite;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaExam1ApplicationTests {
	@Autowired
	UsersRepository usersRepository;
	
	@Test
	void contextLoads() {
	}

	@BeforeEach //테스트하기전에 먼저 실행됨
	void insertTestData() {
		Users user = new Users();
		user.setID(0);
		user.setUsername("Kim one");
		usersRepository.save(user);//save() DB에 저장하는 메서드
		
		user = new Users();
		user.setID(1);
		user.setUsername("Kim two");
		usersRepository.save(user);//save() DB에 저장하는 메서드
		
		user = new Users();
		user.setID(2);
		user.setUsername("Kim three");
		usersRepository.save(user);//save() DB에 저장하는 메서드
		
		user = new Users();
		user.setID(3);
		user.setUsername("Kim four");
		usersRepository.save(user);//save() DB에 저장하는 메서드
	}
	
	@Test
	void findAllTest() {
		List<Users> userList = usersRepository.findAll();
		for(Users u : userList) {
			System.out.println(u.getID() + ", " + u.getUsername());
		}
	}
}
