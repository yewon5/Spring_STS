package com.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {
	@GetMapping("/fragment")
	public String template() {
		return "/template/fragment/fragmentMain";
	}
	
	@GetMapping("/layout")
	public String layoutMain() {
		return "/template/layout/layoutMain";
	}
	
	@GetMapping("/layoutExtend")
	public String layoutExtendMain() {
		return "/template/layoutExtend/layoutExtendMain";
	}
}
