package kr.co.blockcom.board.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.blockcom.board.biz.boardfree.service.BoardFreeService;
import kr.co.blockcom.board.vo.board.BoardFree;
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
		return "/board/write";
	}
	
//	글등록 
	@PostMapping(value = {"/writeSubmit"})
	public ResponseEntity<Integer> writeSubmit(@RequestBody BoardFree vo,HttpServletRequest request){		
		
		try {
			boardFreeService.insertPost(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Integer>(vo.getSeq() , HttpStatus.OK);
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
