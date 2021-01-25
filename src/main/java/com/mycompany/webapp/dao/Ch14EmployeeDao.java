package com.mycompany.webapp.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Employee;

@Repository
public class Ch14EmployeeDao {
	private static final Logger logger = LoggerFactory.getLogger(Ch14EmployeeDao.class);
	
	@Resource
	//bean 관리 객체
	private SqlSessionTemplate sst;
	
	
	public Ch14Employee selectByPk(int employee_id) {
										/* employee.xml에서 가져오기, selectOne은 오직 하나의 객체만을 리턴한다. */				//매개값
		Ch14Employee emp = sst.selectOne("mybatis.mapper.emp.selectByPk", employee_id);
		return emp;
	}
	
}
