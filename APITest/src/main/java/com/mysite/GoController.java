package com.mysite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoController {
	@GetMapping("/")
	public String index() {
		return "go.jsp";
	}
}