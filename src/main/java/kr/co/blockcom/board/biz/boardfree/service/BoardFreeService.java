package kr.co.blockcom.board.biz.boardfree.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.blockcom.board.biz.boardfree.mapper.BoardFreeMapper;
import kr.co.blockcom.board.vo.board.BoardFree;

@Service
public class BoardFreeService {
	private static final Logger logger = LoggerFactory.getLogger(BoardFreeMapper.class);
	
	private final BoardFreeMapper boardFreeMapper;
	
	@Autowired
	BoardFreeService(BoardFreeMapper boardFreeMapper){
		this.boardFreeMapper=boardFreeMapper;
	}
	
	public Map<String, Object> selectPost(Map<String, Object> paramMap) throws Exception{
		logger.info("selectPost : {}", paramMap);
		return boardFreeMapper.selectPost(paramMap);
	}
	
	public int insertPost(BoardFree boardFree) throws Exception{
		logger.info("insertPost : {}", boardFree);
		return boardFreeMapper.insertPost(boardFree);
	}
	
	public List<BoardFree> selectPostList(BoardFree reqVo) throws Exception{
		logger.info("selectPostList : {}", reqVo);
		return boardFreeMapper.selectPostList(reqVo);
	}
	

}
