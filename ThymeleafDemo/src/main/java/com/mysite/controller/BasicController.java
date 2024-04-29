package com.mysite.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic")
public class BasicController {
	@GetMapping("/text-basic")
	public String textbasic(Model model) {
		model.addAttribute("data1", "Hello Spring");
		model.addAttribute("data2", "<b>Hello Spring</b>");
		
		return "basic/text-basic";
	}
	
	@GetMapping("/variable")
	public String variable(Model model) { // 데이터는 HttpServletRequest 여기에 이미 저장되어 있음. model은 리퀘스트 공간안에 존재하고 데이터를 담아주는 그릇일 뿐
		User userA = new User("userA", 20);
		User userB = new User("userB", 30);
		
		//컬렉션 준비
		//list.add("userA") 메서드 없이 객체 생성할때 인자로 바로 넘겨줘도 됨
		List<User> list = new ArrayList<User>(Arrays.asList(userA, userB));
		
		//Map 준비
		Map<String, User> map = new HashMap<>();
		map.put("a", userA);
		map.put("b", userB);
		
		model.addAttribute("userA", userA);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		
		return "basic/variable";
	}
	
	//DTO 클래스 준비
	@Getter
	@RequiredArgsConstructor //변수에 final(상수) 선언해줘야한다
	static class User {
		private final String userName;
		private final int age;
	}
	
	@GetMapping("/basic-objects")
	public String basicObjects(HttpServletRequest req, HttpSession session) { 
		//사용자가 (접속)요청했을때 req객체가 자동으로 생성됨. 그것을 가져오는 것
		req.setAttribute("userName", "홍길동");
		session.setAttribute("sessionData", "hello~~ session");
		
		return "basic/basic-objects";
	}
	
	//Bean 준비
	//직접 뷰에서 가져다 쓰기 (DTO는 서버에서 가져다 씀)
	@Component("helloBean") //bean 등록
	static class HelloBean {
		public String hello(String data) {
			return "Hi! " + data; 
		}
	}
	
	@GetMapping("/date")
	public String date(Model model) {
		model.addAttribute("localDateTime", LocalDateTime.now());
		
		return "basic/date";
	}
	
	@GetMapping("/link")
	public String link(Model model) {
		model.addAttribute("path1", "board");
		model.addAttribute("path2", "write");
		
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		
		return "basic/link";
	}
	
	@GetMapping("/literal")
	public String literal(Model model) {
		model.addAttribute("data", "Hello~~~Spring!!!");
		
		return "basic/literal";
	}
	
	@GetMapping("/operation")
	public String operation(Model model) {
		model.addAttribute("nullData", null);
		model.addAttribute("data", "Hello~~~Spring!!!");
		
		return "basic/operation";
	}
	
	@GetMapping("/attribute")
	public String attribute() {
		return "basic/attribute";
	}
}
