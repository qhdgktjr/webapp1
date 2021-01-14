package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch06Controller.class);

	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch06/content";

	}

	@GetMapping("/forward")
	public String forward() {
		logger.info("실행");
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		logger.info("실행");
				//뷰 이름이 아니기에 앞뒤에 안붙는다.
		return "redirect:/ch06/content";
	}
	
	@GetMapping("/login")
	public String login() {
		logger.info("로그인 성공");
				//뷰 이름이 아니기에 앞뒤에 안붙는다.
		return "redirect:/ch05/content";
	}
	
	@GetMapping("/boardWrite")
	public String boardWrite() {
		logger.info("게시물 저장 성공");
				//뷰 이름이 아니기에 앞뒤에 안붙는다.
		return "redirect:/ch06/boardList";
	}
	
	@GetMapping("/boardList")
	public String boardList() {
		logger.info("게시물 목록 생성");
				//뷰 이름이 아니기에 앞뒤에 안붙는다.
		return "ch06/boardList";
	}
}
