package com.mysite;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserExam {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long userId;
	@Column(name="First_Name") //실제 테이블은 다른 이름으로 매핑
	private String firstName;
	@Column(name="Last_Name")
	private String lastName;
	private int age;
	private LocalDateTime startDate;
	private boolean active;
	
}
