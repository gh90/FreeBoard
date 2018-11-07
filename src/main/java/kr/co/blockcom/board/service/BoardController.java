package kr.co.blockcom.board.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/list")
	public String freeBoardList() {
		
		return "/board/list";
	}
	
	@GetMapping("/write")
	public String freeBoardWrite() {
		
		return "/board/write";
	}

}
