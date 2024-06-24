package com.mysite.entity.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
	public Member() {}
	public Member(int num, String name, String id, String phone, int age) {
		super();
		this.num = num;
		this.name = name;
		this.id = id;
		this.phone = phone;
		this.age = age;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;
	private String name;
	private String id;
	private String phone;
	private int age;
	
	@Override
	public String toString() {
		return "MemberDto [num=" + num + ", name=" + name + ", id=" + id + ", phone=" + phone + ", age=" + age + "]";
	}


}
