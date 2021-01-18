package com.mycompany.webapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class Ch13BoardDao1 {
	private static final Logger logger = LoggerFactory.getLogger(Ch13BoardDao1.class);
	
	public void select() {
		logger.info("게시물을 가져옵니다.1");
	}
}
