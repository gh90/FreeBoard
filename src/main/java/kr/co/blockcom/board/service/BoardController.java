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
import kr.co.blockcom.board.common.util.NullUtil;
import kr.co.blockcom.board.common.util.ResultCodeUtil;
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
	
	private final ResultCodeUtil resultcodeutil;
		
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
	
	//게시글 보기
	@PostMapping(value = {"/postView"})
	public ResponseEntity<ResultVo<BoardFree>> postView(@RequestParam(value="post_id") String seq,HttpSession session) {
		ResultVo<BoardFree> result = new ResultVo<BoardFree>();
		BoardFree vo=new BoardFree();
		try {
			vo = boardFreeService.selectPost(Integer.parseInt(seq));
			if(vo == null) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.NO_POST);
			}else {
				long post_view_time = 0;
				long current_time = System.currentTimeMillis();

				if(session.getAttribute("post_view_time_"+seq)!=null) {
					post_view_time = (long)session.getAttribute("post_view_time_"+seq);
				}
				
				session.setAttribute("post_view_time_"+seq, current_time);				
				
				if(current_time-post_view_time>10*1000 &&!"Y".equals(vo.getSecret_flag())) {
					boardFreeService.updateViewCount(vo);
				}
				
				vo = boardFreeService.selectPost(Integer.parseInt(seq));
				
				if("Y".equals(vo.getSecret_flag())) {
					vo.setTitle("[비밀글]"+vo.getTitle());
					vo.setContent("비밀글입니다.");
				}
				
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS, vo);
			}
			
		} catch (NumberFormatException e) {
			result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PAGE);
			e.printStackTrace();
		} catch (Exception e) {
			result= resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			e.printStackTrace();
		}
		return new ResponseEntity<ResultVo<BoardFree>>(result,HttpStatus.OK);
	}
	
//	글등록 
	@PostMapping(value = {"/writeSubmit"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> writeSubmit(@RequestBody BoardFree vo){
		ResultVo<Integer> result = new ResultVo<Integer>();
		logger.info("## writeSubmit ##");
		logger.debug(vo.toString());
		
		try {
			if(NullUtil.isNullAll(vo, vo.getTitle(),vo.getContent(),vo.getPassword(),vo.getWriter(),vo.getCategory())){
				logger.info("writeSubmit Parameter NULL");
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(1==boardFreeService.insertPost(vo)) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,vo.getSeq());
			}else {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
	}
	
//	글 리스트
	@PostMapping("/postList")
	public ResponseEntity<List<BoardFree>> postList(@RequestBody BoardFree vo) {
		List<BoardFree> resultVoList=new ArrayList<>();
		try {
			resultVoList = boardFreeService.postListWithSecret(vo);
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
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(!passwordVo.getPassword().equals(vo.getPassword())){
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(passwordVo.getPassword().equals(vo.getPassword())) {
				int updateResult=boardFreeService.updatePost(vo);
				if(updateResult==1) {
					result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS);
				}else{
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}
			}else{
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
	}
	
//	글삭제 
	@PostMapping(value = {"/deleteSubmit"})
	public ResponseEntity<ResultVo<Integer>> deleteSubmit(@RequestParam(value="post_id") String seq,@RequestParam(value="delete_password") String password){
		ResultVo<Integer> result = new ResultVo<Integer>();
		BoardFree passwordVo =new BoardFree();
		logger.info("## deleteSubmit ##");
		try {			
			passwordVo = boardFreeService.selectPostPassword(Integer.parseInt(seq));
			if(null == password || "".equals(password)) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(passwordVo.getPassword().equals(password)) {
				int deleteResult=boardFreeService.deletePost(Integer.parseInt(seq));
				if(1==deleteResult) {
					result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS);
				}else {
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}
			}else {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}
		} catch (NumberFormatException e) {
			result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PAGE);
			e.printStackTrace();
		} catch (Exception e) {
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			e.printStackTrace();
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
	}
	
	//비밀글 보기
	@PostMapping(value = {"/secretView"})
	public ResponseEntity<ResultVo<BoardFree>> secretView(@RequestParam(value="post_id") String seq,@RequestParam(value="secret_password") String password){
		ResultVo<BoardFree> result = new ResultVo<BoardFree>();
		BoardFree returnVo = new BoardFree();
		BoardFree passwordVo = new BoardFree();
		try {			
			passwordVo = boardFreeService.selectPostPassword(Integer.parseInt(seq));
			if(null == password || "".equals(password)) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(passwordVo.getPassword().equals(password)) {
				returnVo=boardFreeService.selectPost(Integer.parseInt(seq));
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,returnVo);
			}else {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}
		} catch (NumberFormatException e) {
			result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PAGE);
			e.printStackTrace();
		} catch (Exception e) {
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			e.printStackTrace();
		}
		return new ResponseEntity<ResultVo<BoardFree>>(result , HttpStatus.OK);
	}
	
	//댓글 등록
	@PostMapping(value = {"/writeCommentSubmit"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> writeCommentSubmit(@RequestBody BoardFree vo){
		ResultVo<Integer> result = new ResultVo<Integer>();
		logger.info("## writeCommentSubmit ##");
		logger.debug(vo.toString());
		
		try {
			if(NullUtil.isNullAll(vo,vo.getContent(),vo.getPassword(),vo.getWriter(),vo.getParent_seq(),vo.getCategory())){
				logger.info("writeCommentSubmit Parameter NULL");
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(1==boardFreeService.insertComment(vo)) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,vo.getParent_seq());
			}else {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
	}
	
	//댓글 수정
	@PostMapping(value = {"/modifyCommentSubmit"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> modifyCommentSubmit(@RequestBody BoardFree vo){
		ResultVo<Integer> result = new ResultVo<Integer>();
		logger.info("## modifyCommentSubmit ##");
		logger.debug(vo.toString());
		
		try {
			if(NullUtil.isNullAll(vo,vo.getContent(),vo.getPassword())){
				logger.info("writeCommentSubmit Parameter NULL");
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(1==boardFreeService.updateComment(vo)) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,vo.getParent_seq());
			}else {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
	}
	
	@PostMapping("/commentList")
	public ResponseEntity<List<BoardFree>> commentList(@RequestBody BoardFree vo) {
		List<BoardFree> resultVoList=new ArrayList<>();
		try {
			resultVoList = boardFreeService.selectCommentList(vo);
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
//			test = boardFreeService.selectPost(new HashMap<String,Object>()).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;
	}

}
