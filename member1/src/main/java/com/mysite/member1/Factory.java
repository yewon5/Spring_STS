package com.mysite.member1;

import com.mysite.member1.repository.MemberDao;
import com.mysite.member1.service.ChangePasswordService;
import com.mysite.member1.service.MemberRegisterService;

public class Factory {
	private Factory() {} //싱글톤. 공장은 1개만 필요하니까. private 외부에서 객체를 생성할 수 없도록.
	private static Factory instance = new Factory(); //단 1개만 생성하고 외부에서 불러다 쓸 수 있도록
	public static Factory newInstance() {
		return instance;
	}
	
	//DI패턴
	private MemberRegisterService regSvc = new MemberRegisterService(); 
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	private MemberDao memberDao;
	
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return changePwdSvc;
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
}
