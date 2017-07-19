package com.me.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.crud.bean.Employee;
import com.me.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getEmployees(){
		return employeeMapper.selectWithDeparment(null);
	}
	
}
