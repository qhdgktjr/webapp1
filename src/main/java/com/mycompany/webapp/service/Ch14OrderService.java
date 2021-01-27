package com.mycompany.webapp.service;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.webapp.dao.Ch14OrderDao;
import com.mycompany.webapp.dto.Ch14Order;
import com.mycompany.webapp.dto.Ch14OrderItem;


@Service
public class Ch14OrderService {
	private static final Logger logger = LoggerFactory.getLogger(Ch14OrderService.class);
		
	
	@Resource
	private Ch14OrderDao orderDao;
	
	@Transactional
	public void order(Ch14Order order, List<Ch14OrderItem> orderItem) {
		//orders 테이블에 주문 정보를 저장, 실행 후 ono를 알 수 있다.
		orderDao.insertOrder(order);
		//생성된 주문 번호
		int ono = order.getOno();
		//orderitems 테이블에 주문 상품 정보 저장
		for(Ch14OrderItem oi : orderItem) {
			//주문번호 없기 때문에 위에서 생성된 번호를 입력
			oi.setOno(ono);
			orderDao.insertOrderItem(oi);
		}
	}

	
}
