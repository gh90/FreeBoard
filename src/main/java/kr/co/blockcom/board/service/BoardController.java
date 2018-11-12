package kr.co.blockcom.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import kr.co.blockcom.board.biz.boardfree.service.BoardFreeService;
import kr.co.blockcom.board.vo.board.BoardFree;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
//	private final ObjectMapper objectMapper;
	
	private final BoardFreeService boardFreeService;
		
	@GetMapping("/list")
	public String freeBoardList() {
		return "/board/list";
	}
	
	@GetMapping("/write")
	public String freeBoardWrite() {		
		return "/board/write";
	}
	
	@GetMapping("/postView")
	public String postView() {
		return "/board/postView";
	}
	
	@PostMapping(value = {"/postView"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BoardFree> postView(@RequestBody String post_id) {
		BoardFree vo=new BoardFree();
		try {
			vo = boardFreeService.selectPost(Integer.parseInt(post_id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<BoardFree >(vo,HttpStatus.OK);
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
//	글 내용
	@GetMapping("/postContent")
	public ResponseEntity<BoardFree> postContent(@RequestParam int seq) {
		BoardFree resultVo=new BoardFree();
		try {
			resultVo = boardFreeService.selectPost(seq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<BoardFree>(resultVo,HttpStatus.OK) ;
	}
	
	
	@GetMapping("/test")
	public String testList() {
		String test="";
		try {
//			test = boardFreeService.selectPost(new HashMap<String,Object>()).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;
	}

}
