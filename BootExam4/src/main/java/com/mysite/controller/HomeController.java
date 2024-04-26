package com.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/main")
	public String index() {
		return "index";
	}
	
	@GetMapping("/test1")
	public String test1() {
		return "basic/test1"; // "templates/thymeleaf/" 까지 기본 루트
	}
}
