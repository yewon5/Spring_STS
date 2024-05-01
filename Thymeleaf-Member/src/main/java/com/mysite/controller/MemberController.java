package com.mysite.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.domain.Member;
import com.mysite.repository.MemberDao;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired //주입 받기
	private MemberDao dao;
	
	@GetMapping("/list")
	public String list(Model model) {
		ArrayList<Member> list = (ArrayList<Member>)dao.getSelectAll(); //메서드를 호출하여

	    model.addAttribute("list", list); 
		
		//System.out.println("dao : " + dao.getSelectAll());
		return "member/list";
	}
	
	@GetMapping("/insert")
	public String insert() {
		return "member/insert";
	}
	
	@PostMapping("/insert")
	public String insert(Member member, Model model) {
	    boolean result = dao.insertData(member); //입력받은 데이터를 dao에 전달
	    
	    //insert 성공 여부 코드
	    if(result) {
	    	return "redirect:/member/list";
	    }
	    else {
	    	model.addAttribute("message", "입력 오류입니다.");
	    	return "member/insert";
	    }
	}
	
	@GetMapping("/delete")
    public String delete(@RequestParam("num") int num) {
        dao.deleteData(num);
        return "redirect:/member/list";
    }
	
	
	@GetMapping("/update")
	public String update() {
	    return "member/update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Member member) {
	    dao.updateData(member); // 수정된 회원 정보를 데이터베이스에 업데이트
	    return "redirect:/member/list"; // 수정 후 회원 목록 페이지로 리다이렉트
	}
	
	/*
	@GetMapping("/update")
	public String update() {
		return "member/insert";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Member member) {
	    dao.updateData(member);
	    return "redirect:/member/list"; // 수정 후 목록 페이지로 리다이렉트
	}
	*/
}
