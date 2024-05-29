package com.mysite;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	@Autowired //실행할 수 있는 객체를 주입 받아야함
	private MockMvc mockMvc; 
	
	@Test
	public void testLogin() throws Exception {
		String json = "{\"userid\":\"hong\", \"userpw\":\"1111\"}"; //모든 키와 값은 "" 문자열로 전달
		
		mockMvc.perform(post("/api/employee/test4")
				.contentType(MediaType.APPLICATION_JSON) //요청 방식 선언, JSON타입 선언
				.content(json))
				.andExpect(status().isOk()); //HTTP 응답 상태가 200 OK인지 확인하는 데 사용
	}
}
