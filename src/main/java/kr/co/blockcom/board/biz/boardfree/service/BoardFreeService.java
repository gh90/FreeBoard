package kr.co.blockcom.board.biz.boardfree.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.blockcom.board.biz.boardfree.mapper.BoardFreeMapper;
import kr.co.blockcom.board.vo.board.BoardFree;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardFreeService {
	private static final Logger logger = LoggerFactory.getLogger(BoardFreeMapper.class);
	
	private final BoardFreeMapper boardFreeMapper;
	
	public BoardFree selectPost(int seq) throws Exception{
		logger.info("selectPost : {}", seq);
		return boardFreeMapper.selectPost(seq);
	}
	
	public BoardFree selectPostPassword(int seq) throws Exception{
		logger.info("selectPassword : {}", seq);
		return boardFreeMapper.selectPostPassword(seq);
	}
	
	public int insertPost(BoardFree boardFree) throws Exception{
		logger.info("insertPost : {}", boardFree);
		return boardFreeMapper.insertPost(boardFree);
	}
	
	public int updatePost(BoardFree boardFree) throws Exception{
		logger.info("updatePost : {}", boardFree);
		return boardFreeMapper.updatePost(boardFree);
	}
	
	public int deletePost(int seq) throws Exception{
		logger.info("updatePost : {}", seq);
		return boardFreeMapper.deletePost(seq);
	}
	
	public int updateViewCount(BoardFree boardFree) throws Exception{
		logger.info("updateViewCount : {}", boardFree);
		return boardFreeMapper.updateViewCount(boardFree);
	}
	
	public List<BoardFree> selectPostList(BoardFree reqVo) throws Exception{
		logger.info("selectPostList : {}", reqVo);
		return boardFreeMapper.selectPostList(reqVo);
	}
	
	public List<BoardFree> postListWithSecret(BoardFree reqVo) throws Exception{
		logger.info("selectpostListWtihSecret : {}", reqVo);
		List<BoardFree> returnList = new ArrayList<>();
		returnList=selectPostList(reqVo);
		
		for(BoardFree vo :returnList) {
			if("Y".equals(vo.getSecret_flag())) {
				vo.setTitle("[비밀글]"+vo.getTitle());
			}
		}
		
		return returnList;
	}
	
	
	//대글 관련
	
	public int insertComment(BoardFree boardFree) throws Exception{
		logger.info("insertPost : {}", boardFree);
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

}
