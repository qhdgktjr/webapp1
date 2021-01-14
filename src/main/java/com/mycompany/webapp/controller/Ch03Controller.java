package com.mycompany.webapp.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Ch03Dto;

@Controller
@RequestMapping("/ch03")
public class Ch03Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ch03Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch03/content";
	}
	
	@RequestMapping("/method1")
	public String method1(
			/* 기본으로 String이고, 자동으로 타입을 변환시켜준다 , Date는 기본패턴을 지정해줘야한다.*/
			String param1, int param2, double param3, boolean param4, 
			@DateTimeFormat(pattern="yyyy-MM-dd") Date param5) {
		logger.info("param1 : " + param1);
		logger.info("param1 : " + param2);
		logger.info("param1 : " + param3);
		logger.info("param1 : " + param4);
		logger.info("param1 : " + param5);
		
		return "ch03/content";
	}
	
	@RequestMapping("/method2")
	public String method2(
			/* 기본으로 String이고, 자동으로 타입을 변환시켜준다 , Date는 기본패턴을 지정해줘야한다.*/
			@RequestParam("param1") String arg1, 
			@RequestParam("param2") int arg2,
			double param3,
			boolean param4, 
			@DateTimeFormat(pattern="yyyy-MM-dd") Date param5) {
		logger.info("param1 : " + arg1);
		logger.info("param2 : " + arg2);
		logger.info("param3 : " + param3);
		logger.info("param4 : " + param4);
		logger.info("param5 : " + param5);
		
		return "ch03/content";
	}
	
	@RequestMapping("/method3")
	/* http 500 서버 에러 
	 *  변수 값이 없을 때만 적용, 값이 있으면 입력 값으로 보여준다.
	 * 	기본타입이 String여서 null을 다른타입값으로 변환이 안되어 에러*/
	public String method3(
			 String param1, 
			 @RequestParam(defaultValue="0") int param2,
			 @RequestParam(defaultValue="0.0") double param3,
			 @RequestParam(defaultValue="0") boolean param4) {
		logger.info("param1 : " + param1);
		logger.info("param2 : " + param2);
		logger.info("param3 : " + param3);
		logger.info("param4 : " + param4);
		
		return "ch03/content";
	}
	
	/* class 컴파일 안될 시 클린 한번하기 */
	@RequestMapping("/method4")
	public String method4(Ch03Dto dto) {
			
		logger.info("param1 : " + dto.getParam1());
		logger.info("param2 : " + dto.getParam2());
		logger.info("param3 : " + dto.getParam3());
		logger.info("param4 : " + dto.isParam4());
		logger.info("param5 : " + dto.getParam5());
		return "ch03/content";
	}
	
}
