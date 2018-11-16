package kr.co.blockcom.board.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.blockcom.board.biz.boardfree.service.BoardFreeService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
class BoardContorllerTest {
	
	@Autowired
	private BoardFreeService boardFreeService;
	
	@Test
	void testWriteSubmit() {
//		fail("Not yet implemented");
		
		System.out.println("test");
		try {
			System.out.println(boardFreeService.selectPost(58));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
