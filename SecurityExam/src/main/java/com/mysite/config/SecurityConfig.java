package com.mysite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder encodePwd() { //BCryptPasswordEncoder 패스워드 암호화 시켜주는 객체
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((req)->{
			req.requestMatchers("/user/**").hasRole("USER") //인증된 사람들은 접근허용
				.requestMatchers("/manager/**").hasRole("MANAGER")  //manager라는 역활을 가진사람이 접속할 수 있게
				.requestMatchers("admin/**").hasRole("ADMIN")
				.requestMatchers("/", "/loginForm", "/joinForm").permitAll()
				.anyRequest().authenticated();
		})
		.formLogin((login)->{
			login.loginPage("/loginForm");
		})
		.csrf(csrf->csrf.disable())
		.cors(cors->cors.disable());
		
		return http.build();
	}
}
