package com.mycompany.webapp.re;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class Ch14ServiceRe {
	
	@Resource
	private Ch14EmployeeDaoRe employeeDaoRe;
		
		public Ch14EmployeeRe getEmployeeRe(int id) {
			Ch14EmployeeRe emp = employeeDaoRe.selectByPk(id);
			return emp;
		}
	
}
