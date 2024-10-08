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
public class UserExam { //실제 생성되는 테이블명은 user_exam
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long userId;
	@Column(name="First_Name") //실제 테이블은 다른 이름으로 매핑
	private String firstname;
	@Column(name="Last_Name")
	private String lastname;
	private int age;
	private LocalDateTime startDate;
	private boolean active;
	
}
