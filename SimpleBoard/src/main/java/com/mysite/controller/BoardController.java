package com.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mysite.domain.Board;
import com.mysite.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public String list() {
		return "board/list";
	}
	
	@GetMapping("/board/write")
	public String write() {
		return "board/write";
	}
	
	/* Board Dto를 만들고 아래와 같이 간단하게 코드를 변경하자
	@PostMapping("/board/write")
	public String writeOk(@RequestParam String bTitle, @RequestParam String bWrite, @RequestParam String bContent) { //@RequestParam 생략된 것. dto로 묶어서 한 번에 전달하면 더 좋을 것 같다.
		System.out.println(bTitle + "," + bWrite + "," + bContent);
		return null;
	}
	*/
	
	@PostMapping("/board/write")
	public String writeOk(@ModelAttribute Board board) throws Exception { //dto에 담아서 불러오기. @ModelAttribute 생략된 것
		boardService.write(board);
		return "redirect:/board/list"; 
		// board/list 이렇게하면 서버에서 이동하기때문에 사용자는 또 새로고침을 해야 작성한 글이 보인다.. 클라이언트 측에서 리다이렉션을 수행하여 사용자를 다른 페이지로 이동시킨다.
		// redirect: 접두사를 사용하여 클라이언트 측에서 페이지를 이동시키는 것이 일반적으로 사용자 경험을 향상시키는 방법
	}
}
