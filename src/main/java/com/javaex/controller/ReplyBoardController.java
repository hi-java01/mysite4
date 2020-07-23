package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reply")
public class ReplyBoardController {

	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list() {
		System.out.println("[ReplyController/list()] ");
		
		return "";
	}
	
	
}
