package com.mysite.model;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User1 {
	@Builder //생성자를 빌드업 패턴으로+ 
	public User1(String username, String password, String role, Timestamp createDate, String provider,
			String providerId, String email) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.createDate = createDate;
		this.provider = provider;
		this.providerId = providerId;
		this.email = email;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String username;
	private String password;
	private String role;
	private Timestamp createDate;
	private String provider;
	private String providerId;
	private String email;
		
}
