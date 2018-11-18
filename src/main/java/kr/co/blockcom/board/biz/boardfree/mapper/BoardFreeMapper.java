package kr.co.blockcom.board.biz.boardfree.mapper;

import java.util.List;

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
	 * 패스워드 가져오기
	 * @param param
	 * @return
	 */
	 
	 BoardFree selectPostPassword(int seq) throws Exception;
	 
	 /**
		 * 글 수정
		 * @param param
		 * @return
		 */
	 int updatePost(BoardFree boardFree) throws Exception;
	 
	 /**
		 * 글 삭제
		 * @param param
		 * @return
		 */
	 int deletePost(int seq) throws Exception;
	 
	 /**
		 * 조회수 증가
		 * @param param
		 * @return
		 */
	 int updateViewCount(BoardFree boardFree) throws Exception;
	 
	/**
	 * 글 목록조회
	 * @param param
	 * @return
	 */
	 List<BoardFree> selectPostList(BoardFree boardFree) throws Exception;
	 
	 /**
	 * 댓글 등록 
	 * @param param
	 * @return
	 */
	int insertComment(BoardFree boardFree) throws Exception;
	
	/**
	 * 댓글 가져오기 
	 * @param param
	 * @return
	 */
	List<BoardFree> selectCommentList(BoardFree boardFree) throws Exception;
}
