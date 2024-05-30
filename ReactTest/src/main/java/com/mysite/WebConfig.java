package com.mysite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() { //인터페이스는 객체를 생성할 수 없는데 어떻게? 자동으로 자식클래스에 객체가 상속돼서 생성되는 것

			@Override
			public void addCorsMappings(CorsRegistry registry) { //registry 여기에 필요한 CORS 설정을 추가함
				registry.addMapping("/api/**")//api로 시작하는 모든 파일을 실행 *는 파일 **폴더/파일
					.allowedOrigins("http://localhost:3000") //현업때는 도메인 주소 입력
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.allowedHeaders("*");
			}
		};
	}
}
