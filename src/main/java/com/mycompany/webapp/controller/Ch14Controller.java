package com.mycompany.webapp.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dao.Ch14EmployeeDao;
import com.mycompany.webapp.dto.Ch14Employee;

			//생략시 기본 클래스 이름의 첫글자소문자
@Controller("ch14Controller")
@RequestMapping("/ch14")
public class Ch14Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch14Controller.class);
	
		@GetMapping("/content")
		public String content() {
			
			return "ch14/content";
		}
		
		@Resource
		private DataSource dataSource;
		//관리 객체가 존재한다. <bean>통해서
		
		@GetMapping("/conntest")
		public String conntest() {
							//커넥션 풀을 가져오기
			try {
				//커넥션 풀에서 커넥션 대여
				Connection conn = dataSource.getConnection();
				logger.info("연결 성공");
				//커넥션 풀에 반납
				conn.close();
			} catch (SQLException e) {
				logger.info("연결 실패");
				e.printStackTrace();
			} finally {
				// 항상 실행
			}
			return "ch14/content";
		}
		
		//사실 Service를 주입 받아야 한다.
		@Resource
		private Ch14EmployeeDao employeeDao;
		
		@GetMapping("/employee")
		public String employee(int employee_id) {
			Ch14Employee emp = employeeDao.selectByPk(employee_id);
			logger.info("employee_id : " + emp.getEmployee_id());
			logger.info("first_name : " + emp.getFirst_name());
			logger.info("last_name : " + emp.getLast_name());
			return "ch14/content";
		}
	


}
