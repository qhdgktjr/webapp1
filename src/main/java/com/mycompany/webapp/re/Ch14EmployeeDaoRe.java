package com.mycompany.webapp.re;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Ch14EmployeeDaoRe {
	
	@Resource
	private SqlSessionTemplate sst;
	
	public Ch14EmployeeRe selectByPk(int employee_id) {
		Ch14EmployeeRe emp = sst.selectOne("mybatis.mapper.employee.selectByPk", employee_id);
		return emp;
	}
	
	
}
