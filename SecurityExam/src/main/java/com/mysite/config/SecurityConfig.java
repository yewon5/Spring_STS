package com.mysite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.mysite.auth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) //어노테이션 사용가능하게 설정
public class SecurityConfig {
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() { //패스워드를 암호화 시켜주는 객체
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((req)->{
			req.requestMatchers("/user/**").hasAnyRole("USER", "MANAGER", "ADMIN") //hasAnyRole 적힌 사용자가 이페이지에 다 접속
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")  //manager라는 역활을 가진사람이 접속할 수 있게
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/", "/loginForm", "/joinForm", "/join", "/login").permitAll()
				.anyRequest().authenticated(); //인증만 받으면 접근가능
		})
		.formLogin((login)->{
			login.loginPage("/loginForm")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/"); //로그인이 성공적으로 끝났으면 페이지 이동
		})
		.oauth2Login(login->{
			login.loginPage("/loginForm")
			.userInfoEndpoint((uie)->{
				uie.userService(principalOauth2UserService);
			}); //사용자 처리를 어떻게 하는지
		})
		.logout((logout)->{
			logout.logoutSuccessUrl("/loginForm").invalidateHttpSession(true);
		})
		.csrf(csrf->csrf.disable())
		.cors(cors->cors.disable());
		
		return http.build();
	}
}

/*
 * oauth-client
 * 구글 로그인을 하고 나면 
 * 구글로부터 코드를 받아온다.
 * 그 코드를 가지고 다시 구글에게 요청을 하면 구글로부터 Access Token을 받아온다.
 * 우리의 Security Server가 구글의 사용자 정보에 접근할 수 있는 권한이 생긴 것이다.
 * 우리의 서버는 구글로부터 사용자 정보를 가져와서 그것을 토대로 회원 가입을 진행시킬 수 있다.
 */





