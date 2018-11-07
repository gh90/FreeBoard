package kr.co.blockcom.board.service;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.blockcom.board.biz.boardfree.service.BoardFreeService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
	
	private final BoardFreeService boardFreeService;
	
	@GetMapping("/list")
	public String freeBoardList() {
		
		return "/board/list";
	}
	
	@GetMapping("/write")
	public String freeBoardWrite() {
		String test="";
		try {
			test = boardFreeService.selectPost(new HashMap<String,Object>()).toString();
			System.out.println(test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/board/write";
	}
	
	@GetMapping("/test")
	public String testList() {
		String test="";
		try {
			test = boardFreeService.selectPost(new HashMap<String,Object>()).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;
	}

}
