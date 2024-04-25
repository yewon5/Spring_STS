package com.mysite.domain;

import java.util.Date;

public class Member {
	private Long id; // 자체적으로 아이디값 발생해서 중복되지않는 숫자값 만들어서 자동으로 입력되도록. 
	private String name;
	private String email;
	private String password;
	private String registerDate; //util패키지의 데이트.
	
	//생성자를 통해서 값을 넣어줌. id는 따로 생성할것임.
	public Member(String name, String email, String password, String registerDate) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.registerDate = registerDate;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	
	
}
