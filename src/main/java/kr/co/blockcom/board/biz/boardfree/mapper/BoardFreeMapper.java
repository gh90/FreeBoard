package kr.co.blockcom.board.biz.boardfree.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.blockcom.board.vo.board.BoardFree;

@Repository
public interface BoardFreeMapper {
	/**
	 * 게시글 조회
	 * @param param
	 * @return
	 */
	BoardFree selectPost(int seq) throws Exception;
	
	/**
	 * 글 등록
	 * @param param
	 * @return
	 */
	 int insertPost(BoardFree boardFree) throws Exception;
	 
	/**
	 * 글 목록조회
	 * @param param
	 * @return
	 */
	 List<BoardFree> selectPostList(BoardFree boardFree) throws Exception;

}
