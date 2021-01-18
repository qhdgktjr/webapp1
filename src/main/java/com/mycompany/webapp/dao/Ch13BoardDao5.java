package com.mycompany.webapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("ch13BoardDao5Qualifier")
public class Ch13BoardDao5 implements Ch13BoardDao{
	private static final Logger logger = LoggerFactory.getLogger(Ch13BoardDao5.class);
	
	@Override
	public void insert() {
		logger.info("구현1 실행");
	}
}
