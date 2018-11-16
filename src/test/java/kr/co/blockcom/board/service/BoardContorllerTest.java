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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataSourceConfig.class, BtradeBoardWasSpringConfig.class})
@WebAppConfiguration
public class BoardContorllerTest {
	
	@Autowired
	private BoardFreeService boardFreeService;
	
	@Test
	public void test() {
		System.out.println("test");
		try {
			System.out.println(boardFreeService.selectPost(58));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
