package com.mysite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) //메서드에 보안설정. 컨트롤러에 @Secured("ROLE_ADMIN") 를 사용할 수 있음
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder encodePwd() { //BCryptPasswordEncoder 패스워드 암호화 시켜주는 객체
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((req)->{
			req.requestMatchers("/user/**").hasAnyRole("USER", "MANAGER", "ADMIN") 
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")  //manager라는 역활을 가진사람이 접속할 수 있게
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/", "/loginForm", "/joinForm", "/join", "/login").permitAll() //인증 없이 누구나 접근
				.anyRequest().authenticated(); //위의 경로외에는 인증된 사람만 접근허용
		})
		.formLogin((login)->{
			login.loginPage("/loginForm")
				.loginProcessingUrl("/login") //로그인처리를 누구에게 맡길 것인지 시큐리티한테 알려줌
				.defaultSuccessUrl("/"); //로그인 성공이면 어느 페이지로 이동할 건지
		})
		.logout((logout)->{
			logout.logoutSuccessUrl("/loginForm").invalidateHttpSession(true); //로그아웃되면 어디로 이동할 것인지? 로그아웃되면 세션을 제거해야함
		})
		.csrf(csrf->csrf.disable())
		.cors(cors->cors.disable());
		
		return http.build();
	}
}
