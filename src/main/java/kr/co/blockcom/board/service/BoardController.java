package kr.co.blockcom.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.blockcom.board.biz.boardfree.service.BoardFreeService;
import kr.co.blockcom.board.common.util.model.ResultVo;
import kr.co.blockcom.board.common.util.model.ReturnStatusCode;
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
	
	@PostMapping("/modify")
	public ModelAndView freeBoardModify(@RequestBody BoardFree vo) {
		ModelAndView mv =new ModelAndView("/board/modify");
		
		
		return mv;
	}
	
	@GetMapping("/postView")
	public String postView(Model model, @RequestParam String post_id) {
		model.addAttribute("post_id", post_id);
		return "/board/postView";
	}
	
	@PostMapping(value = {"/postView"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<BoardFree>> postView(@RequestBody String post_id,HttpSession session) {
		ResultVo<BoardFree> result = new ResultVo<BoardFree>();
		BoardFree vo=new BoardFree();
		try {
			vo = boardFreeService.selectPost(Integer.parseInt(post_id));
			if(vo==null) {
				result.setReturnStatusCode(ReturnStatusCode.NO_POST);
			}else {
				if("Y".equals(vo.getSecret_flag())) {
					
				}
				
				
				long post_view_time=0;
				long current_time =System.currentTimeMillis();

				if(session.getAttribute("post_view_time_"+post_id)!=null) {
					post_view_time=(long)session.getAttribute("post_view_time_"+post_id);
				}
				
				session.setAttribute("post_view_time_"+post_id, current_time);
				
				
				if(current_time-post_view_time>10*1000) {
					boardFreeService.updateViewCount(vo);
					
				}
				result.setReturnStatusCode(ReturnStatusCode.SUCCESS);
				vo = boardFreeService.selectPost(Integer.parseInt(post_id));
				result.setData(vo);
			}
			
		} catch (NumberFormatException e) {
			result.setReturnStatusCode(ReturnStatusCode.INVALID_PAGE);
			e.printStackTrace();
		} catch (Exception e) {
			result.setReturnStatusCode(ReturnStatusCode.FAIL);
			e.printStackTrace();
		}
		return new ResponseEntity<ResultVo<BoardFree>>(result,HttpStatus.OK);
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
	
//	글수정 
	@PostMapping(value = {"/modifySubmit"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> modifySubmit(@RequestBody BoardFree vo){
		ResultVo<Integer> result = new ResultVo<Integer>();
		BoardFree passwordVo =new BoardFree();
		logger.info("## modifySubmit ##");
		logger.debug(vo.toString());
		try {
			passwordVo=boardFreeService.selectPostPassword(vo.getSeq());
			if(passwordVo==null) {
				result.setReturnStatusCode(ReturnStatusCode.NO_POST);
			}else if(!passwordVo.getPassword().equals(vo.getPassword())){
				result.setReturnStatusCode(ReturnStatusCode.WRONG_PASSWORD);
			}else if(passwordVo.getPassword().equals(vo.getPassword())) {
				int updateResult=boardFreeService.updatePost(vo);
				if(updateResult==1) {
					result.setReturnStatusCode(ReturnStatusCode.SUCCESS);
				}else{
					result.setReturnStatusCode(ReturnStatusCode.FAIL);
				}
			}else{
				result.setReturnStatusCode(ReturnStatusCode.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setReturnStatusCode(ReturnStatusCode.FAIL);
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
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
