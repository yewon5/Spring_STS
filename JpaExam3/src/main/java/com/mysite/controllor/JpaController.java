package com.mysite.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.entity.member.Member;
import com.mysite.entity.member.MemberDto;
import com.mysite.repository.MemberRepository;

@Controller
@RequestMapping("/jpa")
public class JpaController {
	@Autowired
	private MemberRepository memberRepository;
	
    @GetMapping("/memberWrite")
    public String memberWriteForm(@RequestParam(value="num", required=false) Integer num, Model model) { //false 클라이언트가 이 파라미터를 제공하지 않더라도 오류가 발생하지 않도록 설정
    	
    	if(num != null) {
    		// 수정
    		model.addAttribute("formTitle", "Modification");
    		
    		Member member = memberRepository.findById(num).orElse(null);
    		model.addAttribute("memberDto", member); //수정페이지에서 form내용이 출력돼야 하니까 전달받아야함
    		
    	}
    	else {
    		// 신규 등록
    		model.addAttribute("formTitle", "Registration");
    		model.addAttribute("memberDto", new MemberDto());
    	}
    	
        return "jpa/memberRegister";
    }
    
    @PostMapping("/memberWrite") // GET 방식과 POST방식이기때문에 RESTful 방식을 이용하여 매핑을 같게 할 수 있다.
    public String memberWriteOk(MemberDto memberDto, Model model) {
    	Member member = memberDto.toEntity();
    	System.out.println(member.toString()); //MemberDto [num=0, name=Ye, id=0, phone=123-4567, age=20] 저장 전
    	
    	Member saved = memberRepository.save(member);
    	System.out.println(saved.toString()); //MemberDto [num=1, name=Ye, id=0, phone=123-4567, age=20] 저정 후엔 넘버 적용
    	
    	return "redirect:/"; //등록 후 다시 새로고침 되어야 해서 리다이렉트
    }
    
    @GetMapping("/memberList")
    public String memberList(Model model) {
    	List<Member> members = memberRepository.findAll();
    	model.addAttribute("members", members); //모델에 담아서 페이지 넘겨주기
    	return "jpa/memberList";
    }
    
    @GetMapping("/memberDelete")
    public String memberDelete(@RequestParam(value="num", required = false) Integer num) {
    	memberRepository.deleteById(num);
    	return "redirect:/";
    }
}
