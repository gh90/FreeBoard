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
		System.out.println("test");
		try {
			System.out.println(boardFreeService.selectPost(58));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
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
}
