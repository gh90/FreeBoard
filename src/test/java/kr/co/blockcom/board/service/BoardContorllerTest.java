package kr.co.blockcom.board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.blockcom.board.biz.boardfree.service.BoardFreeService;
import kr.co.blockcom.board.config.BtradeBoardWasSpringConfig;
import kr.co.blockcom.board.config.DataSourceConfig;
import kr.co.blockcom.board.vo.board.BoardFree;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataSourceConfig.class, BtradeBoardWasSpringConfig.class})
@WebAppConfiguration
public class BoardContorllerTest {
	
	@Autowired
	private BoardFreeService boardFreeService;
	
//	@Test
	public void selectPostTest() {
		try {
			System.out.println(boardFreeService.selectPost(58));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
	public void selectPostPasswordTest() {
		try {
			System.out.println(boardFreeService.selectPostPassword(58));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
//	@Test
	public void selectCommentPasswordTest() {
		try {
			System.out.println(boardFreeService.selectCommentPassword(73));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
//	@Test
	public void insertPostTest() {
		BoardFree vo =new BoardFree();
		vo.setTitle("제목 1");
		vo.setContent("테스트");
		vo.setWriter("테스터");
		vo.setPassword("1234");
		vo.setCategory(1);
		vo.setSecret_flag("N");
		vo.setComment_flag("N");
		vo.setNotice_flag("N");
		try {
			System.out.println(boardFreeService.insertPost(vo));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	@Test
	public void updatePostTest() {
		BoardFree vo =new BoardFree();
		vo.setSeq(79);
		vo.setTitle("제목 1");
		vo.setContent("테슽으 1231");
		vo.setPassword("1234");
		
		try {
			System.out.println(boardFreeService.updatePost(vo));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	@Test
	public void updatePostDeleteFlagTest() {
		try {
			System.out.println(boardFreeService.updatePostDeleteFlag(79));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	@Test
	public void updateViewCountTest() {
		BoardFree vo =new BoardFree();
		vo.setSeq(78);
		try {
			System.out.println(boardFreeService.updateViewCount(vo));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	@Test
	public void selectPostListTest(){
		BoardFree vo =new BoardFree();
		vo.setStartPost(10);
		vo.setPagingSize(10);
		
		try {
			System.out.println(boardFreeService.selectPostList(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	@Test
	public void selectPostCountTest() {
		BoardFree vo =new BoardFree();
		try {
			System.out.println(boardFreeService.selectPostCount(vo));
			vo.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void postListWithSecretTest(){
		BoardFree vo =new BoardFree();		
		vo.setCategory(1);
		vo.setNowPage(1);
		vo.setPagingSize(10);
		vo.setBlockSize(10);
		try {
			System.out.println(boardFreeService.postListWithSecret(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	@Test
	public void insertCommentTest() {
		BoardFree vo =new BoardFree();
		vo.setContent("댓글 테이스트");
		vo.setParent_seq(61);
		vo.setWriter("테스터");
		vo.setPassword("1234");
		vo.setCategory(2);
		vo.setSecret_flag("N");
		
		try {
			System.out.println(boardFreeService.insertComment(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void selectCommentListTest() {
		BoardFree vo =new BoardFree();
		vo.setParent_seq(6);
		
		
		try {
			System.out.println(boardFreeService.selectCommentList(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void updateCommentTest() {
		BoardFree vo =new BoardFree();
		vo.setSeq(70);
		vo.setContent("으,허허허허");
		try {
			System.out.println(boardFreeService.updateComment(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateCommentDeleteFlagTest() {
		try {
			System.out.println(boardFreeService.updateCommentDeleteFlag(77));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
