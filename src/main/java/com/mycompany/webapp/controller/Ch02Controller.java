package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
/* 공통경로에서는 get,post맵핑은 불가 */
@RequestMapping("/ch02")
public class Ch02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
		@RequestMapping("/content")
		public String content() {
			logger.info("실행");
			return "ch02/content";
		}
		
		
		/* @RequestMapping(value="/request1") GET, POST 방식을 둘다 지원, value= 기본값 */		
		@RequestMapping(value="/getMethod" , method=RequestMethod.GET)
		/* @RequestMapping(value="/request1", method=RequestMethod.POST) */
		public String getMethod() {
			logger.info("실행");
			return "ch02/content";
		}
		
		/* @RequestMapping(value="/postMethod" , method=RequestMethod.POST) */
		@PostMapping("/postMethod")
		public String postMethod() {
			logger.info("실행");
			return "ch02/content";
		}
		/* 가입 양식을 얻는다.*/
		/* @RequestMapping(value="/join" , method=RequestMethod.GET) */
		@GetMapping("/join")
		public String joinForm() {
			logger.info("회원가입 양식을 보냄");
			return "ch02/joinform";
		}
		/* 가입 양식을 보낸다.*/
		/* @RequestMapping(value="/join" , method=RequestMethod.POST) */
		@PostMapping("/join")
		public String join() {
			logger.info("회원가입 처리함");
			return "ch02/content";
		}
		
		/* @RequestMapping(value="/joinAsync" , method=RequestMethod.GET) */
		@GetMapping("/joinAsync")
		public String joinFormAsync() {
			logger.info("회원가입 양식을 보냄");
						/* 응답부분  html조각*/
			return "ch02/joinformAsync";
		}
		
}
