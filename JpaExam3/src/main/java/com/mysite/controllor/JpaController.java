package com.mysite.controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	public String memberWriteForm(@RequestParam(value="num", required=false) Integer num, Model model) {
		
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
	
	@PostMapping("/memberWrite")  // GET 방식과 POST방식이기때문에 RESTful 방식을 이용하여 매핑을 같게 할 수 있다.
	public String memberWriteOk(MemberDto memberDto, Model model) {	
		Member member = memberDto.toEntity();
		System.out.println(member.toString()); //MemberDto [num=0, name=Ye, id=0, phone=123-4567, age=20] 저장 전
		
		Member saved = memberRepository.save(member);
		System.out.println(saved.toString()); //MemberDto [num=1, name=Ye, id=0, phone=123-4567, age=20] 저정 후엔 넘버 적용
		
		return "redirect:/"; //등록 후 다시 새로고침 되어야 해서 리다이렉트
	}
	
	/*
	// 검색 쿼리 만들기
	@GetMapping("/memberList")
	public String memberList(
				Model model, 
				Pageable pageable, 
				@RequestParam(value="searchCate", required = false, defaultValue = "") String searchCate,
				@RequestParam(value="searchKeyword", required = false, defaultValue = "") String searchKeyword) {
		//List<Member> members = memberRepository.findAll();
		//Page<Member> members = memberRepository.findAll(pageable);
		//Page<Member> members = memberRepository.findByNameContaining(searchKeyword, pageable);
		
		Page<Member> members = null;
		if(searchCate.equals("name")) {
			members = memberRepository.findByNameContaining(searchKeyword, pageable);
			
			// 이름과 아이디가 같은 사람을 조회
			// members = memberRepository.findByNameAndId("홍길동", "hong", pageable);
			
			// 이름 또는 아이디에 "홍"이라는 값을 갖는 회원을 조회
			// members = memberRepository.findByNameContainsOrIdContains("홍", "홍", pageable);
		}
		else if(searchCate.equals("id")) {
			members = memberRepository.findById(searchKeyword, pageable);
		}
		else if(searchCate.equals("phone")) {
			// members = memberRepository.findByPhoneContaining(searchKeyword, pageable);
			// members = memberRepository.findByPhone(searchKeyword, pageable);
			members = memberRepository.findByPhoneLike("%"+searchKeyword+"%", pageable);
		}
		else {
			members = memberRepository.findAll(pageable);
		}
		
		
//		System.out.println("총 페이지 수 : " + members.getTotalPages());
//		System.out.println("총 레코드 수 : " + members.getTotalElements());
//		System.out.println("현재 페이지 번호 : " + members.getNumber());
//		System.out.println("한 페이지에 보여질 레코드 수 : " + members.getSize());
//		System.out.println("정렬 : " + members.getSort());
//		System.out.println(members.getPageable());
		
		
		model.addAttribute("members", members);  //컨트롤러부터 members라는 이름으로 전달 받음. 모델에 담아서 페이지 넘겨주기
		return "jpa/memberList";
	}
	*/
	
	/*
	// 정렬 쿼리 만들기
	@GetMapping("/memberList")
	public String memberList(
				Model model, 
				@PageableDefault(size=5, sort="name", direction=Sort.Direction.ASC) Pageable pageable,
				@RequestParam(value="searchKeyword", required = false, defaultValue = "") String searchKeyword) {

		Page<Member> members = null;

		if (searchKeyword.isEmpty()) {
			// 전체 검색
			members = memberRepository.findAll(pageable);
		}
		else {
			members = memberRepository.findByNumGreaterThanEqualOrderByNameAsc(Integer.parseInt(searchKeyword), pageable); 
		}
		
		model.addAttribute("members", members); 
		model.addAttribute("searchKeyword", searchKeyword);
		return "jpa/memberList";
	}
	*/
	
	/*
	// PageRequest.of()
	@GetMapping("/memberList")
	public String memberList(
				Model model, 
				Pageable pageable,
				@RequestParam(value="searchKeyword", required = false, defaultValue = "") String searchKeyword) {

		Page<Member> members = null;

		if (searchKeyword.isEmpty()) {
			// 전체 검색
			pageable = PageRequest.of(pageable.getPageNumber(), 5, Sort.Direction.ASC, "num"); //(시작 페이지 번호, 한페이지에 보여질 글) 0으로 고정하면 페이지 이동이 안됨. pageable.getPageNumber()해야 페이지 이동됨
			members = memberRepository.findAll(pageable); 
		}
		else {
			pageable = PageRequest.of(pageable.getPageNumber(), 5, Sort.by("age").descending().and(Sort.by("name").descending())); // 나이/이름 내림차순으로 다중정렬이 가능함
			members = memberRepository.findByNameContaining(searchKeyword, pageable); // 정렬은 안되고 이름만 검색
		}
		
		model.addAttribute("members", members); 
		model.addAttribute("searchKeyword", searchKeyword);
		return "jpa/memberList";
	}
	*/
	
	
	// @Query
	@GetMapping("/memberList")
	public String memberList(
				Model model, 
				@PageableDefault(size=5) Pageable pageable,
				@RequestParam(value="searchKeyword", required = false, defaultValue = "") String searchKeyword) {

		Page<Member> members = null;

		if (searchKeyword.isEmpty()) {
			// 전체 검색
			members = memberRepository.findAll(pageable); 
		}
		else { // 이름으로 전달 받으면
			PageRequest.of(pageable.getPageNumber(), 2);
			//members = memberRepository.findByName(searchKeyword, pageable); 
			//members = memberRepository.findByName(searchKeyword, 20, pageable);
			members = memberRepository.findByNameContainsAndAgeBetweenOrderByNameAsc(searchKeyword, 20, 30, pageable);
		}
		
		model.addAttribute("members", members); 
		model.addAttribute("searchKeyword", searchKeyword);
		return "jpa/memberList";
	}
	
	
	@GetMapping("/memberDelete")
	public String memberDelete(@RequestParam(value="num", required = false) Integer num) {
		memberRepository.deleteById(num);
		return "redirect:/jpa/memberList";
	}
	
	@GetMapping("/memberDetail")  //DB를 조회해서 꺼내온 다음에 상세페이지를 넘겨줘야함
	public String memberDetail(@RequestParam(value="num", required = false) Integer num, Model model) {
		Member member = memberRepository.findById(num).orElse(null);
		model.addAttribute("member", member);
		
		return "jpa/memberDetail";
	}
}












