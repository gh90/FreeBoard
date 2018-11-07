package kr.co.blockcom.board.biz.boardfree.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.blockcom.board.biz.boardfree.mapper.BoardFreeMapper;

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
	

}
