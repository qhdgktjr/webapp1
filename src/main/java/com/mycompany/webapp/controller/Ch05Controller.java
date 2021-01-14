package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch03Controller.class);

	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch05/content";

	}

	@GetMapping("/method1")
	/* 헤더명의 값을 저장	스프링이 자동으로 객체의내용을 찾아준다. */
	public String method1(@RequestHeader("User-Agent") String userAgent) {
		logger.info("실행");
		/* logger.info(userAgent); */
		if (userAgent.contains("Edg")) {
			logger.info("브라우저의 종류 : 엣지 ");
		} else if (userAgent.contains("Chrome")) {
			logger.info("브라우저의 종류 : 크롬");
		} else if (userAgent.contains("Trident/7.0")) {
			logger.info("브라우저의 종류 : IE11");
		} else if (userAgent.contains("MSIE")) {
			logger.info("브라우저의 종류 : IE이하");
		}
		return "ch05/content";

	}
	
	@GetMapping("/method2")
						//WAS가 제공하는 요청 저장 객체
	public String method2(HttpServletRequest request) {
		logger.info("실행");
			 String userAgent = request.getHeader("User-Agent");
		
		
		if (userAgent.contains("Edg")) {
			logger.info("브라우저의 종류 : 엣지 ");
		} else if (userAgent.contains("Chrome")) {
			logger.info("브라우저의 종류 : 크롬");
		} else if (userAgent.contains("Trident/7.0")) {
			logger.info("브라우저의 종류 : IE11");
		} else if (userAgent.contains("MSIE")) {
			logger.info("브라우저의 종류 : IE이하");
		}
		return "ch05/content";

	}
}
