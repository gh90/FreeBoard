package kr.co.blockcom.board.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = {"/xhr"}, method = RequestMethod.GET)
	public ResponseEntity<String> listAjax(){
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
