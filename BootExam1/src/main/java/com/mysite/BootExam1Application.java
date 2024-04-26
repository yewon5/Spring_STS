package com.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication 모든 설정과 어노테이션이 다 포함되어있다.
@SpringBootApplication
public class BootExam1Application {

	public static void main(String[] args) {
		SpringApplication.run(BootExam1Application.class, args); //run메서드가 스프링 부트를 실행함
	}
}
