package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
				//공통 경로
@RequestMapping("/ch00")
public class Ch00Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch00Controller.class);
	
	// 세부 경로 http://.../webapp1/ch01/content
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch00/content";
		
	}
}
