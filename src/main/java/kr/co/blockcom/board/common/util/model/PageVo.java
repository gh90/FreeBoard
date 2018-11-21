package kr.co.blockcom.board.common.util.model;

import lombok.Data;

@Data
public class PageVo{
	//페이지당 게시물 수
	private int pagingSize;
	//화면당 페이지 수
	private int blockSize;
	//현재 페이지
	private int nowPage;
	//전체 페이지 갯수
	private int totalPage;
	//페이지 게시물 시작
	private int startPost;
	//페이지 게시물 끝
	private int EndPost;
	//전체 게시물 수
	private int totalPost;
	//현재 페이지가 속한 블록
	private int nowBlock;
	//현재 페이지가 속한 블록의 시작 페이지
	private int startPage;
	//현재 페이지가 속한 블록의 마지막 페이지
	private int endPage;
	//마지막 블록
	private int lastBlock;
}
