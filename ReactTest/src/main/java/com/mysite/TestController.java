package com.mysite;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@RestController
@RequestMapping("/api/employee")
public class TestController {
	@GetMapping("/test1")
	public String test1() {
		return "<div>Test1입니다.<div>";
	}
	
	@GetMapping("/test2")
	public LoginDTO test2() {
		LoginDTO login = new LoginDTO("hong", "1234"); //객체 생성
		return login;
		// {"userid":"hong","userpw":"1234"} JSON 형식으로 클라이언트에 전달됨
	}
	
	@GetMapping("/test3") //HashMap, ArrayList
	public HashMap<String, LoginDTO> test3() {
		
		/*
		//ArrayList<LoginDTO>은 .add
		ArrayList<LoginDTO> list = new ArrayList<LoginDTO>();
		list.add(new LoginDTO("hong", "1111"));
		list.add(new LoginDTO("hong", "1111"));
		
		return list; //JSON 형식으로 전달
		*/
		
		//HashMap<String, LoginDTO>은 .put 맵형식은 JSON과 가장 유사
		HashMap<String, LoginDTO> map = new HashMap<String, LoginDTO>();
		map.put("1", new LoginDTO("hong", "1111"));
		map.put("2", new LoginDTO("hong", "1111"));
		
		return map; //JSON 형식으로 키와 값으로 전체를 {}로 묶어서 전달됨
	}
	
	@PostMapping("/test4")
	public String test4(@RequestBody LoginDTO login) { 
		//(LoginDTO login) 이렇게하면 데이터만 전달 받는데, 
		//사용자가 보내는 헤더정보를 같이 묶어서 받을때는 @RequestBody를 사용
		//클라이언트 없이 코드를 테스트를 하는 방법은? Mock up을 사용
		String info = login.getUserid() + ", " + login.getUserpw();
		return info;
	}
	
	
	//편의상 내부에 DTO클래스를 만든다 -> @Data 게터세터, 생성자 준비
	@Data
	static class LoginDTO {
		public LoginDTO() {} //com.mysite.EmployeeController$LoginDTO 기본생성자 없음 오류. 
		private String userid;
		//@JsonIgnore //해당 변수는 제외하고 전달
		private String userpw;
		
		public LoginDTO(String userid, String userpw) {
			super();
			this.userid = userid;
			this.userpw = userpw;
		}
	}
}
