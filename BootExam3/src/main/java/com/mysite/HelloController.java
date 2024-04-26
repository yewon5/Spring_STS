package com.mysite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링 설정 파일에서 어노테이션 스캐너를 설정해줘야 사용가능했지만, 스프링 부트는 메인메서드에 @SpringBootApplication를 통해서 모든 설정을 한 번에 처리한다.
//@RestController view 없이 바로 결과를 보여지게 할 수 있다. ajax 비동기 방식
@RestController
@Controller
public class HelloController {
	@GetMapping("/hello") //hello 요청이 들어왔을때 실행 (주소창)
	public String hello() {
		return "<h1>컨트롤러 실행</h1>";
	}
}
