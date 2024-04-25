package com.mysite.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.domain.Member;
import com.mysite.domain.RegisterRequest;
import com.mysite.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
	
	@GetMapping("/login")
	public void login() {} //페이지이름과 이동페이지 이름이 같으면 void로하고 경로 생략 가능		
	
	@PostMapping("/login")
	public String loginOk(String id, String pwd, HttpServletRequest req) throws Exception {
		String msg = URLEncoder.encode("아이디 또는 비밀번호가 일치하지 않습니다.", "utf-8");
		
		try {
			Member member = memberService.selectWithPass(id, pwd);
			
			HttpSession session= req.getSession();
			session.setAttribute("id", member.getEmail());
		}	
		catch(Exception err) {
			return "redirect:/member/login?msg=" + msg;		
		}
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/add")
	public String step1() {
		return "member/step1";
	}
	
	@PostMapping("/step2")
	public String step2(@RequestParam(value="agree", defaultValue="false") boolean agree) { //@RequestParam을 통해서 미체크시 F값으로 넘어오게 설정
		if(!agree) { //미체크시 다음단계로 안 넘어감
			return "member/step1";
		}
		return "member/step2";
	}
	
	//DB에 저장. step2에서 저장한 값을 가져와야함
	//dto - dao - mybatis
	@PostMapping("/step3")
	public String step3(@ModelAttribute RegisterRequest dto) throws Exception {
		memberService.registerMember(dto);
		return "member/step3";
	}
}
