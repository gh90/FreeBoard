package kr.co.blockcom.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
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
	@PostMapping(value = {"/writeSubmit"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> writeSubmit(@RequestBody BoardFree vo){
		logger.info("## writeSubmit ##");
		logger.debug(vo.toString());
		
		try {
			boardFreeService.insertPost(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Integer>(vo.getSeq() , HttpStatus.OK);
	}
	
//	글 리스트
	@PostMapping("/postList")
	public ResponseEntity<List<BoardFree>> postList(@RequestBody BoardFree vo) {
		List<BoardFree> resultVoList=new ArrayList<>();
		try {
			resultVoList = boardFreeService.selectPostList(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<BoardFree>>(resultVoList,HttpStatus.OK) ;
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
