package com.mysite.domain;

public class RegisterRequest {
	// views/member/step2.jsp에서 입력한 값을 묶어서 저장하기 위한 Dto
	
	private String email;
	private String password;
	private String passwordConfirm;
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//비밀번호 일치 확인 메서드
	public boolean isPasswordEqualToPasswordConfirm() {
		return password.equals(passwordConfirm);
	}
}
