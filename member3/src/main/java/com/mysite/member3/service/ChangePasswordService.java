package com.mysite.member3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mysite.member3.model.Member;
import com.mysite.member3.repository.MemberDao;

@Service("chSvc")
public class ChangePasswordService {
	private MemberDao memberdao;
	
	//여기는 세터메서드로 구현.
	@Autowired
	public void setMemberdao(MemberDao memberdao) {
		this.memberdao = memberdao;
	}


	public void changePassword(String email, String oldPass, String newPass) {
		// 해당 이메일이 존재하는지 여부 검사
		
		//여기서 member객체가 생성되었으므로 member메서드 사용이가능.
		Member member = memberdao.selectByEmail(email);
		if(member == null) {
			System.out.println("해당 회원이 없습니다.");
			return;
		}
		
		// 현재 비밀번호가 맞는지 검사
		if(!member.getPassword().equals(oldPass)) {
			System.out.println("현재 비밀번호가 맞지 않습니다.");
			return;
		}
		
		member.setPassword(newPass);
		System.out.println("비밀번호가 변경되었습니다.");
		memberdao.update(member);
	}
}
