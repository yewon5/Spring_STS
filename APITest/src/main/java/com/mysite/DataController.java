package com.mysite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //데이터도 전달하고 , 뷰도 이동하고
@CrossOrigin(origins = "*", methods = RequestMethod.GET) // cors 오류 해결 코드
public class DataController {
	@ResponseBody //데이터를 보여주기 위한
	@GetMapping("/data/test1")
	public String getTest1() {
		String data = "{\"name\":\"John\", \"age\":20}"; //{name:John, age:20} 이건 전체문자열이라 어떤게 키와 값인지 모르니까 구분해줘야함
		return data;
	}
	
	//DTO
	static class User{
		private Long id;
		private String name;
		private int age;
		
		public User(Long id, String name, int age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
		}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
	
	@ResponseBody //뷰의 역할
	@GetMapping("/data/users")
	public List<User> getAllUsers(){ // API 서버 메서드
		ArrayList<User> userList = new ArrayList<>(); //dto 담음
		
		userList.add(new User(1L, "John", 30));
		userList.add(new User(2L, "Alice", 35));
		userList.add(new User(3L, "Bob", 40));
		
		return userList;
	}
}
