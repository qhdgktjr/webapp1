package com.mycompany.webapp.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Order;
import com.mycompany.webapp.dto.Ch14OrderItem;

@Repository
public class Ch14OrderDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14OrderDao.class);
	
	@Resource
	//bean 관리 객체
	private SqlSessionTemplate sst;
	
	public int insertOrder(Ch14Order order) {
		int rows = sst.insert("orders.orderinsert", order);
		return rows;
	}
	
	public int insertOrderItem(Ch14OrderItem orderItem) {
		int rows = sst.insert("orders.orderiteminsert", orderItem);
		return rows;
	}
	

	
}
