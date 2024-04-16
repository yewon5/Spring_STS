package com.mysite.member1.service;

import java.util.Collection;
import java.util.Date;

import com.mysite.member1.model.Member;
import com.mysite.member1.model.RegisterRequest;
import com.mysite.member1.repository.MemberDao;

//DAO한테 바로 전달하는 것이 아니라 서비스(중간역할)에 전달
public class MemberRegisterService {
	private MemberDao memberDao = new MemberDao();
	
	public void register(RegisterRequest req) { 
		//5개의 값이 DAO에 전달돼야 DAO가 DB에 저장할 수 있다.
		//같은 이메일주소가 있는지 확인
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			System.out.println("같은 이메일 주소가 있습니다.");
			return;
		}
		
		//두개의 암호 일치하는지 확인
		if(!req.getPassword().equals(req.getPasswordConfirm())) {
			System.out.println("패스워드를 잘 못 입력하였습니다.");
			return;
		}
		
		//Member객체로 묶어주기
		Member newMem = new Member(req.getName(), req.getEmail(), req.getPassword(), new Date());
		memberDao.insert(newMem);
	}
	
	public Collection<Member> selectAll() {
		return memberDao.selectAll();
	}
}