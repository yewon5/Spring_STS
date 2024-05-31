package com.mysite;

import lombok.Data;

//DTO
@Data
public class Employee {
	public Employee() {}
	
	private int id;
	private String name;
	private String email;

}
