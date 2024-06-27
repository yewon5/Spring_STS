package com.mysite.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.model.User1;
import com.mysite.repository.User1Repository;

import lombok.RequiredArgsConstructor;

//시큐리티는 컨트롤러를 통해서 정보를 수집해간다.
@Controller
@RequiredArgsConstructor  // final변수라서 @Autowired 설정이 안된다.
public class IndexController {
	private final User1Repository user1Repository;
	private final BCryptPasswordEncoder bCrypt;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//사용자페이지에 접근
	@GetMapping("/user")
	@ResponseBody //일부만 RESTful API 방식을 사용할 것이기때문에 해당 메서드에만 어노테이션 등록
	public String user() {
		return "user";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "manager";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/join")
	@ResponseBody //이게 없으면 view로 이동하기때문에 html을 찾는다.
	public String join(User1 user) {
		// DB에 저장
		user.setRole("ROLE_USER"); //반드시 저장할떄는 ROLE_라는 접두사가 붙어야한다.
		
		String encPwd = bCrypt.encode(user.getPassword());
		user1Repository.save(user);
		
		return "redirect:/loginForm";
	}
	
	@GetMapping("/joinProc")
	@ResponseBody
	public String joinProc() {
		return "인증된 사용자만 들어올 수 있음";
	}
	
	
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }
	
	@GetMapping("/joinForm")
	public String joinForm() {
	    return "joinForm";
	}
}
