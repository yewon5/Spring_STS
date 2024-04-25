package com.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.domain.Member;
import com.mysite.domain.RegisterRequest;
import com.mysite.persistance.MemberDao;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void registerMember(RegisterRequest dto) throws Exception {
		memberDao.registerMember(dto);
	}

	@Override
	public Member selectMember(String email) throws Exception {
		return null;
	}

	@Override
	public Member selectWithPass(String email, String pw) throws Exception {
		return memberDao.selectWithPass(email, pw);
	}
}
