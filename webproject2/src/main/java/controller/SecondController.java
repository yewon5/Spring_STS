package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
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
		
		ModelAndView mv = new ModelAndView("/WEB-INF/views/second.jsp"); //객체에 담아서 주소 전달
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
	public String fourthPage(String name, int age, double point, Model model) { //ModelAndView방식 말고 Model객체 사용하여 전달
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setPoint(point);
		
		model.addAttribute("user", user);
		
		return "/WEB-INF/views/fourth.jsp"; //컨트롤러가 fourth(view)에게 넘겨주기 위해서는 DTO가 필요하다. User DTO 준비
	}
	
	
	//ModelAndView 클래스 방식
	@GetMapping("/fourth")
	public ModelAndView fourthPage(String name, int age, double point) {
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
	
	@RequestMapping("/fifth")
	public ModelAndView fifthPage(ModelAndView mv) { //ModelAndView객체를 생성하고, list에 데이터를 담아준다.
		List list = new ArrayList();
		list.add("쌀국수");
		list.add("돼지갈비");
		list.add("뇨끼");
		list.add("피자");
		
		//데이터를 Map으로 묶어보기
		Map map = new HashMap<String, String>();
		map.put("f1", "딸기");
		map.put("f2", "파인애플");
		map.put("f3", "대추방울토마토");
		
		//2개의 리스트를 1개로 묶기위해 Map을 사용
		List listDay = new ArrayList<String>();
		listDay.add("목요일");
		listDay.add("금요일");
		listDay.add("토요일");
		listDay.add("일요일");
		
		List listMovie = new ArrayList<String>();
		listMovie.add("범죄도시");
		listMovie.add("아이언맨");
		listMovie.add("트와일라잇");
		listMovie.add("암살");
		
		Map maps = new HashMap<String, List>(); //key는 String 값은 list
		maps.put("day", listDay);
		maps.put("movie", listMovie);
		
		
		mv.setViewName("/WEB-INF/views/fifth.jsp"); //ModelAndView 객체에 뷰의 이름을 설정
		mv.addObject("foods", list); //ModelAndView 객체에 리스트를 "foods"라는 이름으로 추가 
		mv.addAllObjects(map); //Map addAllObjects
		mv.addAllObjects(maps);
				
		return mv; //foods와 fifth.jsp 뷰를 반환
	}
}
