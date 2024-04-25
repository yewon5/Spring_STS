package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.domain.Board;
import com.mysite.service.BoardService;

@Controller
@RequestMapping("/board") // @GetMapping("/board/list")을 ("/list") 간단하게 줄일 수 있다.
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//글 목록
	@GetMapping("/list")
	public ModelAndView list() throws Exception {
		List<Board> list = boardService.getList();

		ModelAndView mv = new ModelAndView("board/list");
		mv.addObject("list", list);
		return mv;
	}
	
	//글쓰기
	@GetMapping("/write") //get방식이라서 write.jsp에서 보낸 요청을 받지 못함
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
	
	//글 목록 확인
	@PostMapping("/write") //post 방식으로 등록한 메서드만 받음
	public String writeOk(@ModelAttribute Board board) throws Exception { //dto에 담아서 불러오기. @ModelAttribute 생략된 것
		boardService.write(board);
		return "redirect:/board/list"; 
		// board/list 이렇게하면 서버에서 이동하기때문에 사용자는 또 새로고침을 해야 작성한 글이 보인다.. 
		// redirect: 접두사를 사용하여 클라이언트 측에서 리다이렉션을 수행하여 사용자를 다른 페이지로 이동시킨다.
	}
	
	//작성글 확인
	@GetMapping("/read")
	public String read(int bNo, Model model) throws Exception {
		Board board = boardService.read(bNo);
		model.addAttribute(board);
	    return "board/read";
	}
	
	
}
