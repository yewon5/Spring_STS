package com.mysite.persistance;

import com.mysite.domain.Member;
import com.mysite.domain.RegisterRequest;

public interface MemberDao {
	//회원 가입
	public void registerMember(RegisterRequest dto) throws Exception;
	
	//회원 조회
	public Member selectMember(String email) throws Exception;
	
	//회원 인증
	public Member selectWithPass(String email, String pw) throws Exception;

}
