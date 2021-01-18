package com.mycompany.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class Ch13Service3 {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Service3.class);
	
	public void method() {
		logger.info("관리객체 서비스3 실행");
	}
}
