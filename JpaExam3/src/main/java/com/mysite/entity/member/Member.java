package com.mysite.entity.member;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
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
