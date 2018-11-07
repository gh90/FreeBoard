package kr.co.blockcom.board.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/freeBoard")
	public String freeBoardList() {
		
		return "/freeBoard";
	}
	
	@RequestMapping("/freeBoard/write")
	public String freeBoardWrite() {
		
		return "/freeBoard/write";
	}

}
