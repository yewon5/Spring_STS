package com.mysite.member3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysite.member3.model.Member;
import com.mysite.member3.repository.MemberDao;

@Component("InfoSvc")
public class InfoByemailService {
	private MemberDao memberdao;
	
	
	public InfoByemailService() {}
	
	@Autowired
	public InfoByemailService(MemberDao memberdao) {
		this.memberdao = memberdao;
	}

	public Member InfoByEmail(String email) {
		Member member = memberdao.selectByEmail(email);
		
		if(member == null) {
			System.out.println("존재하지 않는 회원입니다.");
			return null;
		}
		
		
		return member;
	}
}
