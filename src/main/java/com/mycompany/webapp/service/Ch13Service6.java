package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13BoardDao;


@Service
public class Ch13Service6 {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Service6.class);
	
	@Resource(name="ch13BoardDao5")
	private Ch13BoardDao boardDao5;
	
	@Autowired
	@Qualifier("ch13BoardDao6Qualifier")
	private Ch13BoardDao boardDao6;
	
	
	public void method() {
		logger.info("관리객체 서비스6 실행");
		boardDao5.insert();
		boardDao6.insert();
	}
}
