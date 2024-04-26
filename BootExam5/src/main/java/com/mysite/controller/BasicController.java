package com.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicController {
	/*
	//Model 방식. Model은 뷰로 전달할 데이터를 담는 객체
	@GetMapping("/basic/text-basic")
	public String basic(Model model) {
		String data1 = "Hello Model";
		
		model.addAttribute("data1", data1);
		
		return "basic/text-basic";
	}
	*/
	
	//ModelAndView 방식. 뷰 이름과 모델 객체를 함께 저장하는 클래스
	@GetMapping("/basic/text-basic")
	public ModelAndView basic(ModelAndView model) {
		String data1 = "Hello ModelAndView";
		
		model.addObject("data1", data1);
		model.setViewName("basic/text-basic");
		
		return model;
	}
}
