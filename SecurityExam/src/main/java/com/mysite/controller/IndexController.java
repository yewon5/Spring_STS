package com.mysite.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.auth.PrincipalDetails;
import com.mysite.model.User1;
import com.mysite.repository.User1Repository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor //생성자 주입받음
public class IndexController {
	private final User1Repository user1Repository;
	private final BCryptPasswordEncoder bCrypt;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}	
	
	@GetMapping("/user")
	@ResponseBody //api를 제공하는 메서드, 없으면 뷰로 이동
	public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("user : " + principalDetails.getUser());
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
	
	@PostMapping("/join")
	public String join(User1 user) {
		// DB에 저장
		user.setRole("ROLE_USER"); //ROLE_가 붙어야 된다
		
		String encPwd = bCrypt.encode(user.getPassword()); //자동으로 암호화 시켜줌
		user.setPassword(encPwd); //암호화
		
		user1Repository.save(user);
		return "redirect:/loginForm";
	}
	
	@GetMapping("/joinProc")
	@ResponseBody
	public String joinProc() {
		return "인증된 사용자만 들어올 수 있는 곳";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	@ResponseBody
	public String info() {
		return "개인 정보";
	}
	
	// @PreAuthorize(), @PostAuthorize(), 메서드가 실행될때 허가처리, 사후처리
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
	@GetMapping("/data")
	@ResponseBody
	public String data() {
		return "데이터 정보";
	}
	
	@GetMapping("/test/login")
	@ResponseBody
	public String testLogin(Authentication auth,
			@AuthenticationPrincipal UserDetails ud) {
		System.out.println("/test/login========================================");
		System.out.println("Authentication : " + auth.getPrincipal());
		
		PrincipalDetails principalDetails = (PrincipalDetails)auth.getPrincipal();
		System.out.println("사용자 정보 : " + principalDetails.getUser());
		System.out.println("이름 : " + ud.getUsername());
		
		return "세션(UserDetails) 정보 확인";
	}
	
	@GetMapping("/test/oauth/login")
	@ResponseBody
	public String testOAuthLogin(Authentication auth,
			@AuthenticationPrincipal OAuth2User oau) {
		System.out.println("/test/login========================================");
		System.out.println("Authentication : " + auth.getPrincipal());
		
		OAuth2User principalDetails = (OAuth2User)auth.getPrincipal();
		System.out.println("사용자 정보 : " + oau.getAttributes());
		
		
		return "세션(OAuth2User) 정보 확인";
	}
}









