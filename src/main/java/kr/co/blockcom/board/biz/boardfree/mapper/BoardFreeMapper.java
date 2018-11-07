package kr.co.blockcom.board.biz.boardfree.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardFreeMapper {
	
	/**
	 * 게시글 조회
	 * @param param
	 * @return
	 */
	Map<String, Object> selectPost(Map<String, Object> paramMap) throws Exception;

}
