package kr.co.blockcom.board.biz.boardfree.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.blockcom.board.biz.boardfree.mapper.BoardFreeMapper;
import kr.co.blockcom.board.common.util.PageUtil;
import kr.co.blockcom.board.common.util.model.PageListVo;
import kr.co.blockcom.board.vo.board.BoardFree;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardFreeService {
	private static final Logger logger = LoggerFactory.getLogger(BoardFreeMapper.class);
	
	private final BoardFreeMapper boardFreeMapper;
	
	private final PageUtil pageUtil;
	
	public BoardFree selectPost(int seq) throws Exception{
		logger.info("selectPost : {}", seq);
		return boardFreeMapper.selectPost(seq);
	}
	
	public BoardFree selectPostPassword(int seq) throws Exception{
		logger.info("selectPassword : {}", seq);
		BoardFree vo = new BoardFree();
		vo = boardFreeMapper.selectPassword(seq);
		if("N".equals(vo.getComment_flag())) {
			return vo;
		}else {
			return null;
		}
	}
	
	public BoardFree selectCommentPassword(int seq) throws Exception{
		logger.info("selectPassword : {}", seq);
		BoardFree vo = new BoardFree();
		vo = boardFreeMapper.selectPassword(seq);
		if("Y".equals(vo.getComment_flag())) {
			return vo;
		}else {
			return null;
		}
	}
	
	public int insertPost(BoardFree boardFree) throws Exception{
		logger.info("insertPost : {}", boardFree);
		return boardFreeMapper.insertPost(boardFree);
	}
	
	public int updatePost(BoardFree boardFree) throws Exception{
		logger.info("updatePost : {}", boardFree);
		return boardFreeMapper.updatePost(boardFree);
	}
	
	public int updatePostDeleteFlag(int seq) throws Exception{
		logger.info("updatePostDeleteFlag : {}", seq);
		return boardFreeMapper.updatePostDeleteFlag(seq);
	}
	
	public int updateViewCount(BoardFree boardFree) throws Exception{
		logger.info("updateViewCount : {}", boardFree);
		return boardFreeMapper.updateViewCount(boardFree);
	}
	
	public List<BoardFree> selectPostList(BoardFree reqVo) throws Exception{
		logger.info("selectPostList : {}", reqVo);
		return boardFreeMapper.selectPostList(reqVo);
	}
	
	public int selectPostCount(BoardFree reqVo) throws Exception{
		logger.info("selectPostCount : {}", reqVo);
		return boardFreeMapper.selectPostCount(reqVo);
	}
	
	public PageListVo<BoardFree> postListWithSecret(BoardFree reqVo) throws Exception{
		logger.info("postListWithSecret : {}", reqVo);
		PageListVo<BoardFree> returnList = new PageListVo<BoardFree>();
		List<BoardFree> pageList = new ArrayList<BoardFree>();
		reqVo.setTotalPost(selectPostCount(reqVo));
		pageUtil.setPaging(reqVo);
		pageList = selectPostList(reqVo);
		
		for(BoardFree vo :pageList) {
			if("Y".equals(vo.getSecret_flag())) {
				vo.setTitle("[비밀글]"+vo.getTitle());
			}
		}
		returnList.setData(pageList);
		returnList.setPageVo(reqVo);
		
		return returnList;
	}
	
	
	//대글 관련
	
	public int insertComment(BoardFree boardFree) throws Exception{
		logger.info("insertComment : {}", boardFree);
		return boardFreeMapper.insertComment(boardFree);
	}
	
	public List<BoardFree> selectCommentList(BoardFree boardFree) throws Exception{
		logger.info("selectCommentList : {}", boardFree);
		return boardFreeMapper.selectCommentList(boardFree);
	}
	
	public int updateComment(BoardFree boardFree) throws Exception{
		logger.info("updateComment : {}", boardFree);
		return boardFreeMapper.updateComment(boardFree);
	}
	
	public int updateCommentDeleteFlag(int seq) throws Exception{
		logger.info("updateCommentDeleteFlag : {}", seq);
		return boardFreeMapper.updateCommentDeleteFlag(seq);
	}
	

}
