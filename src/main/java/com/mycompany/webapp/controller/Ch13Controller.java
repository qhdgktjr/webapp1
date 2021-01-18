package com.mycompany.webapp.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.service.Ch13Service1;
import com.mycompany.webapp.service.Ch13Service2;
import com.mycompany.webapp.service.Ch13Service3;
import com.mycompany.webapp.service.Ch13Service4;
import com.mycompany.webapp.service.Ch13Service5;

			//생략시 기본 클래스 이름의 첫글자소문자
@Controller("ch13Controller")
@RequestMapping("/ch13")
public class Ch13Controller {
	//Field
	private static final Logger logger = LoggerFactory.getLogger(Ch13Controller.class);
	
	// 주입 방법1
	@Resource
	private Ch13Service1 service1;
	
	@Resource
	private Ch13Service2 service2;
	
	
	private Ch13Service3 service3;
	private Ch13Service4 service4;
	
	@Resource
	private Ch13Service5 service5;
	
	
	
	// 주입 방법2
	//Constructor
	/* 생성자를 만들 후 매개값으로 주입, 
	  @Autowired 자동적으로 관리하는 객체를 통해 값을 주입 */
	
	/*  @Autowired 
	  public Ch13Controller(Ch13Service1 service1, Ch13Service2 service2) { 
	  logger.info("실행");
	  this.service1 = service1;
	  this.service2 = service2;
	  }
	 */
	
	
	// 주입 방법3
	//Method
	/*
	  @Autowired 
	 public void setService1(Ch13Service1 service1) {
		  logger.info("스프링에서 자동으로 메소드 실행1"); 
		  this.service1 = service1;
	 
	 	  }
	  
	  @Autowired 
	  public void setService2(Ch13Service2 service2) {
		  logger.info("스프링에서 자동으로 메소드 실행2"); 
		  this.service2 = service2;
	 
	 	  }
	 */
	
	public void setService3(Ch13Service3 service3) {
		logger.info("스프링에서 자동으로 메소드 실행3"); 
		this.service3 = service3;
	}
	
	public void setService4(Ch13Service4 service4) {
		logger.info("스프링에서 자동으로 메소드 실행4"); 
		this.service4 = service4;
	}
	
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch13/content";
	}
	
	@GetMapping("/service1")
	public String service1() {
		logger.info("서비스1 실행");
		service1.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service2")
	public String service2() {
		logger.info("서비스2 실행");
		service2.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service3")
	public String service3() {
		logger.info("서비스3 실행");
		service3.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service4")
	public String service4() {
		logger.info("서비스4 실행");
		service4.method();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/service5")
	public String service5() {
		logger.info("서비스5 실행");
		service5.method();
		return "redirect:/ch13/content";
	}

}
