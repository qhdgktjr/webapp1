package com.mycompany.webapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch05Controller.class);

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
	
	@GetMapping("/method3")
	public String method3(HttpServletRequest request,
						  HttpServletResponse response) {
		//쿠키생성
		Cookie cookie1 = new Cookie("memberId", "winter");
		Cookie cookie2 = new Cookie("loginStatus", "ok");
		
		//쿠키를 응답 헤더에 추가해서 클라이언트로  보내기
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		//응답 본문을 생성하느나 뷰 리턴
		return "ch05/content";
	}
	
	@GetMapping("/method4")
	public String method4(HttpServletRequest request,
						  HttpServletResponse response) {
		//브라우저가 요청 헤더에 보낸 쿠키 값을 읽기
		Cookie[] cookieArr = request.getCookies();
		for(Cookie cookie : cookieArr) {
			String name = cookie.getName();
			String value = cookie.getValue();
			logger.info(name + " : " + value);
		}
		//응답 본문을 생성하느나 뷰 리턴
		return "ch05/content";
	}
	
	@GetMapping("/method5")
	public String method5(
			//쿠키이름과 변수 이름이 같으면 생략 @CookieValue("memberId")
			@CookieValue String memberId,
			@CookieValue String loginStatus) {
		//브라우저가 요청 헤더에 보낸 쿠키 값을 읽기
			logger.info("memberId" + " : " + memberId);
			logger.info("loginStatus" + " : " + loginStatus);
		
		//응답 본문을 생성하느나 뷰 리턴
		return "ch05/content";
	}
}
