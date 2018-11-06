package kr.co.blockcom.board.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showMessage")
public class SampleController {

	/**
	 * List View
	 */
	@GetMapping
	public String list() throws Exception{
		return "/showMessage";
	}
}
