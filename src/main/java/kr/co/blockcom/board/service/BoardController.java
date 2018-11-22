package kr.co.blockcom.board.service;

import java.util.ArrayList;
import java.util.List;

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

import kr.co.blockcom.board.biz.boardfree.service.BoardFreeService;
import kr.co.blockcom.board.common.util.NullUtil;
import kr.co.blockcom.board.common.util.ResultCodeUtil;
import kr.co.blockcom.board.common.util.model.PageListVo;
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
		
	@GetMapping("/list/view")
	public String freeBoardList() {
		return "/board/list";
	}
	
	@GetMapping("/write/view")
	public String freeBoardWrite() {		
		return "/board/write";
	}
	
	@GetMapping("/post/view")
	public String postView(Model model, @RequestParam String post_id) {
		model.addAttribute("post_id", post_id);
		return "/board/postView";
	}
	
	//게시글 보기
	@PostMapping(value = {"/post/view/xhr"})
	public ResponseEntity<ResultVo<BoardFree>> postView(@RequestParam(value="post_id") String seq) {
		logger.info("## post/view ##");
		ResultVo<BoardFree> result = new ResultVo<BoardFree>();
		BoardFree vo=new BoardFree();
		try {
			vo = boardFreeService.selectPost(Integer.parseInt(seq));
			if(vo == null) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.NO_POST);
			}else {
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
	@PostMapping(value = {"/write/xhr"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> writeSubmit(@RequestBody BoardFree boardFree){
		ResultVo<Integer> result = new ResultVo<Integer>();
		logger.info("## write/xhr ##");
		
		try {
			if(NullUtil.isNullAll(boardFree, boardFree.getTitle(),boardFree.getContent(),boardFree.getPassword(),boardFree.getWriter(),boardFree.getCategory())){
				logger.info("write/xhr Parameter NULL");
				result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PARAMETER);
			}else if(1==boardFreeService.insertPost(boardFree)) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,boardFree.getSeq());
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
	@PostMapping("/postList/xhr")
	public ResponseEntity<ResultVo<PageListVo<BoardFree>>> postList(@RequestBody BoardFree vo) {
		ResultVo<PageListVo<BoardFree>> result = new ResultVo<PageListVo<BoardFree>>();
		PageListVo<BoardFree> pageResult = new PageListVo<BoardFree>();
		try {
			if(NullUtil.isNullAll(vo,vo.getCategory(),vo.getNowPage())) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PARAMETER);
			}else {
				pageResult = boardFreeService.postListWithSecret(vo);
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,pageResult);				
			}
			
		} catch (Exception e) {
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			e.printStackTrace();
		}
		return new ResponseEntity<ResultVo<PageListVo<BoardFree>>>(result,HttpStatus.OK) ;
	}
	
//	글수정 
	@PostMapping(value = {"/modify/xhr"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> modifySubmit(@RequestBody BoardFree boardFree){
		ResultVo<Integer> result = new ResultVo<Integer>();
		BoardFree passwordVo =new BoardFree();
		logger.info("## modify/xhr ##");
		try {
			if(NullUtil.isNullAll(boardFree,boardFree.getSeq(),boardFree.getContent(),boardFree.getPassword())) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PARAMETER);
			}else {
				passwordVo=boardFreeService.selectPostPassword(boardFree.getSeq());
				
				if(NullUtil.isNullAll(passwordVo,passwordVo.getPassword())) {
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}else if(!passwordVo.getPassword().equals(boardFree.getPassword())){
					result = resultcodeutil.getResultInfo(ReturnStatusCode.WRONG_PASSWORD);
				}else if(passwordVo.getPassword().equals(boardFree.getPassword())) {
					int updateResult=boardFreeService.updatePost(boardFree);
					if(updateResult==1) {
						result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS);
					}else{
						result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
					}
				}else{
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
	}
	
//	글삭제 
	@PostMapping(value = {"/delete/xhr"})
	public ResponseEntity<ResultVo<Integer>> deleteSubmit(@RequestParam(value="post_id") String seq,@RequestParam(value="delete_password") String password){
		ResultVo<Integer> result = new ResultVo<Integer>();
		BoardFree passwordVo =new BoardFree();
		logger.info("## delete/xhr ##");
		try {			
			passwordVo = boardFreeService.selectPostPassword(Integer.parseInt(seq));
			if(NullUtil.isNullAll(password,passwordVo,passwordVo.getPassword())) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(passwordVo.getPassword().equals(password)) {
				int deleteResult=boardFreeService.deletePost(Integer.parseInt(seq));
				if(1==deleteResult) {
					result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS);
				}else {
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}
			}else {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.WRONG_PASSWORD);
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
	@PostMapping(value = {"/secretView/xhr"})
	public ResponseEntity<ResultVo<BoardFree>> secretView(@RequestParam(value="post_id") String seq,@RequestParam(value="secret_password") String password){
		logger.info("## secretView/xhr ##");
		ResultVo<BoardFree> result = new ResultVo<BoardFree>();
		BoardFree returnVo = new BoardFree();
		BoardFree passwordVo = new BoardFree();
		try {			
			passwordVo = boardFreeService.selectPostPassword(Integer.parseInt(seq));
			if(NullUtil.isNullAll(password,passwordVo,passwordVo.getPassword())) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			}else if(passwordVo.getPassword().equals(password)) {
				returnVo=boardFreeService.selectPost(Integer.parseInt(seq));
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,returnVo);
			}else if(!passwordVo.getPassword().equals(password)) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.WRONG_PASSWORD);
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
	@PostMapping(value = {"/writeComment/xhr"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> writeCommentSubmit(@RequestBody BoardFree vo){
		ResultVo<Integer> result = new ResultVo<Integer>();
		logger.info("## writeComment/xhr ##");
		
		try {
			if(NullUtil.isNullAll(vo,vo.getContent(),vo.getPassword(),vo.getWriter(),vo.getParent_seq(),vo.getCategory())){
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
	@PostMapping(value = {"/modifyComment/xhr"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> modifyCommentSubmit(@RequestBody BoardFree vo){
		ResultVo<Integer> result = new ResultVo<Integer>();
		BoardFree passwordVo =new BoardFree();
		logger.info("## modifyComment/xhr ##");
		
		try {
			if(NullUtil.isNullAll(vo,vo.getSeq(),vo.getContent(),vo.getPassword())) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PARAMETER);
			}else {
				passwordVo = boardFreeService.selectCommentPassword(vo.getSeq());
				if(NullUtil.isNullAll(passwordVo,passwordVo.getPassword())){
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}else if(vo.getPassword().equals(passwordVo.getPassword())){
					if(1==boardFreeService.updateComment(vo)) {
						result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,vo.getParent_seq());
					}else {
						result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
					}
				}else if(!vo.getPassword().equals(passwordVo.getPassword())){
					result = resultcodeutil.getResultInfo(ReturnStatusCode.WRONG_PASSWORD);
				}else{
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
	}
	
	//댓글 삭제
	
	@PostMapping(value = {"/deleteComment/xhr"},consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultVo<Integer>> deleteCommentSubmit(@RequestBody BoardFree vo){
		ResultVo<Integer> result = new ResultVo<Integer>();
		BoardFree passwordVo =new BoardFree();
		logger.info("## deleteComment/xhr ##");
		
		try {
			if(NullUtil.isNullAll(vo,vo.getSeq(),vo.getPassword())) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PARAMETER);
			}else {
				passwordVo = boardFreeService.selectCommentPassword(vo.getSeq());
				if(NullUtil.isNullAll(passwordVo,passwordVo.getPassword())){
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}else if(vo.getPassword().equals(passwordVo.getPassword())){
					if(1==boardFreeService.deleteComment(vo.getSeq())) {
						result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,vo.getParent_seq());
					}else {
						result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
					}
				}else if(!vo.getPassword().equals(passwordVo.getPassword())){
					result = resultcodeutil.getResultInfo(ReturnStatusCode.WRONG_PASSWORD);
				}else{
					result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
		}
		return new ResponseEntity<ResultVo<Integer>>(result , HttpStatus.OK);
	}
	
	@PostMapping("/commentList/xhr")
	public ResponseEntity<ResultVo<List<BoardFree>>> commentList(@RequestBody BoardFree vo) {
		List<BoardFree> resultVoList=new ArrayList<>();
		ResultVo<List<BoardFree>> result = new ResultVo<List<BoardFree>>();
		try {
			if(NullUtil.isNullAll(vo,vo.getParent_seq())) {
				result = resultcodeutil.getResultInfo(ReturnStatusCode.INVALID_PARAMETER);
			}else {
				resultVoList = boardFreeService.selectCommentList(vo);
				result = resultcodeutil.getResultInfo(ReturnStatusCode.SUCCESS,resultVoList);
			}
		} catch (Exception e) {
			result = resultcodeutil.getResultInfo(ReturnStatusCode.FAIL);
			e.printStackTrace();
		}
		return new ResponseEntity<ResultVo<List<BoardFree>>>(result,HttpStatus.OK) ;
	}
}
