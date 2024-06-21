package com.mysite.entity.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	private int num;
	private String name;
	private String id;
	private String phone;
	private int age;
	
	@Override
	public String toString() {
		return "MemberDto [num=" + num + ", name=" + name + ", id=" + id + ", phone=" + phone + ", age=" + age + "]";
	}
	
	//dto를 가지고 멤버 객체로 변환해주는 역할
	public Member toEntity() {
		return new Member(num, name, id, phone, age);
	}
}
