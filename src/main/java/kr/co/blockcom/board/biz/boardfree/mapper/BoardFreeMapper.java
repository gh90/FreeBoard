package kr.co.blockcom.board.biz.boardfree.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.blockcom.board.vo.board.BoardFree;

@Repository
public interface BoardFreeMapper {
	/**
	 * 게시글 조회
	 * @param seq
	 * @return
	 */
	BoardFree selectPost(int seq) throws Exception;
	
	/**
	 * 글 등록
	 * @param BoardFree
	 * @return
	 */
	int insertPost(BoardFree boardFree) throws Exception;
	 
	 /**
	 * 패스워드 가져오기
	 * @param seq
	 * @return
	 */
	 
	 BoardFree selectPassword(int seq) throws Exception;
	 
	 /**
		 * 글 수정
		 * @param BoardFree
		 * @return
		 */
	 int updatePost(BoardFree boardFree) throws Exception;
	 
	 /**
		 * 글 삭제플래그 변경
		 * @param seq
		 * @return
		 */
	 int updatePostDeleteFlag(int seq) throws Exception;
	 
	 /**
		 * 조회수 증가
		 * @param BoardFree
		 * @return
		 */
	 int updateViewCount(BoardFree boardFree) throws Exception;
	 
	/**
	 * 글 목록조회
	 * @param BoardFree
	 * @return
	 */
	 List<BoardFree> selectPostList(BoardFree boardFree) throws Exception;
	 
	 /**
	 * 글 갯수
	 * @param BoardFree
	 * @return
	 */
	 int selectPostCount(BoardFree boardFree) throws Exception;
	 
	 /**
	 * 댓글 등록 
	 * @param BoardFree
	 * @return
	 */
	int insertComment(BoardFree boardFree) throws Exception;
	
	/**
	 * 댓글 가져오기 
	 * @param BoardFree
	 * @return
	 */
	List<BoardFree> selectCommentList(BoardFree boardFree) throws Exception;
	
	 /**
	 * 댓글 수정
	 * @param BoardFree
	 * @return
	 */
	int updateComment(BoardFree boardFree) throws Exception;
	
	/**
	 * 댓글 삭제플래그 업데이트
	 * @param seq
	 * @return
	 */
	int updateCommentDeleteFlag(int seq) throws Exception;
	
	
}
