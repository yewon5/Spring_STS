package com.mysite.member1.service;

import com.mysite.member1.model.Member;
import com.mysite.member1.repository.MemberDao;

public class ChangePasswordService {
	private MemberDao memberDao = new MemberDao();

	public void changePassword(String email, String oldPass, String newPass) {
		//해당 이메일이 존재하는지, 존재하지 않을 때는 중단
		Member member = memberDao.selectByEmail(email);
		System.out.println("member : " + member);
		if(member == null) {
			System.out.println("해당 멤버가 없습니다.");
			return;
		}
		
		//현재 비밀번호가 맞는지 검사
		if(!member.getPassword().equals(oldPass)) {
			System.out.println("현재 비밀번호가 맞지 않습니다.");
			return;
		}
		
		member.setPassword(newPass);
		memberDao.update(member);
	}
}
