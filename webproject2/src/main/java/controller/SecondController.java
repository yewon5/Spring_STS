package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.User;

@Controller
public class SecondController {
	@RequestMapping("/first")
	protected String firstPage() { //페이지만 이동할거라면 문자열로 넘겨줘도됨
		return "/WEB-INF/views/first.jsp";
	}
	
	@RequestMapping("/second")
	public ModelAndView secondPage(HttpServletRequest req) { 
		String p1 = req.getParameter("p1");
		String p2 = req.getParameter("p2");
		
		// System.out.println(p1 + ", " + p2); 
		//클라이언트가 보내온 데이터를 잘 받아왔다면 second.js에 전달해야한다.
		//페이지이동만 하는 것이 아니라 값을 전달하려면 ModelAndView 클래스로 설정하고, addObject 메서드를 통해서 second.jsp에 값을 전달한다.
		
		ModelAndView mv = new ModelAndView("/WEB-INF/views/second.jsp");
		mv.addObject("param1", p1);
		mv.addObject("param2", p2);
		
		return mv;
	}
	
	/*
	//@RequestMapping(value="/third", method=RequestMethod.POST), @PostMapping 사용으로 간단하게
	@PostMapping("/third")
	public String thirdPage(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String point = req.getParameter("point");

		System.out.println(name + ", " + age + ", " + point);
		return ("/WEB-INF/views/third.jsp");
	}
	*/
	
	//@RequestParam 넘어오는 데이터를 바로 매개변수에서 처리함
	//public String thirdPage(String name, int age, double point) 어노테이션이 동일할 경우 생략가능
	@PostMapping("/third")
	public String thirdPage(@RequestParam("name") String irum, @RequestParam int age, @RequestParam double point) {
		System.out.println(irum + ", " + age + ", " + point);
		return "/WEB-INF/views/third.jsp";
	}
	
	/*
	//Model객체 방식
	@GetMapping("/fourth")
	public String fourthPage(String name, int age, double point, Model model) { //ModelAndView방식 말고 Model객체 사용
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setPoint(point);
		
		model.addAttribute("user", user);
		
		return "/WEB-INF/views/fourth.jsp"; //컨트롤러가 fourth(view)에게 넘겨주기 위해서는 DTO가 필요하다. User DTO 준비
	}
	
	
	//ModelAndView 클래스 방식
	@GetMapping("/fourth")
	public ModelAndView fourthPage(String name, int age, double point) { //ModelAndView방식 말고 Model객체 사용
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setPoint(point);
		
		ModelAndView mv = new ModelAndView("/WEB-INF/views/fourth.jsp");
		mv.addObject("user", user);
		
		return mv; //컨트롤러가 fourth(view)에게 넘겨주기 위해서는 DTO가 필요하다. User DTO 준비
	}
	*/
	
	@GetMapping("/fourth")
	public ModelAndView fourthPage(@ModelAttribute User user, @RequestParam String chk) { //변수를 따로따로 받지 않고 User user적어주면 넘어오는 파라미터를 
		ModelAndView mv = new ModelAndView("/WEB-INF/views/fourth.jsp");
		mv.addObject("user", user);
		mv.addObject("chk", chk);
		
		return mv; //컨트롤러가 fourth(view)에게 넘겨주기 위해서는 DTO가 필요하다. User DTO 준비
	}
}
