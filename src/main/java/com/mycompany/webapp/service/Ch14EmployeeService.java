package com.mycompany.webapp.service;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14EmployeeDao;
import com.mycompany.webapp.dto.Ch14Employee;


@Service
public class Ch14EmployeeService {
	private static final Logger logger = LoggerFactory.getLogger(Ch14EmployeeService.class);
		
	
	@Resource
	private Ch14EmployeeDao employeeDao;
	
	public Ch14Employee getEmployee(int employee_id) {
		Ch14Employee employee = employeeDao.selectByPk(employee_id);
		
		return employee;
	}
	
	
}
