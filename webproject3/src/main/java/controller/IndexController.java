package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;



/*
//@Controller 없이 기존 방식
public class IndexController extends AbstractController{
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("/WEB-INF/views/index.jsp");
	}
}




@Controller
public class IndexController {
	@GetMapping("/")
	public String index() {
		return "/WEB-INF/views/index.jsp";
	}
}
*/