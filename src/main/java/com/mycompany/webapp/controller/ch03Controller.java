package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch03")
public class ch03Controller {

	private static final Logger logger = LoggerFactory.getLogger(ch03Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch03/content";
	}
}
