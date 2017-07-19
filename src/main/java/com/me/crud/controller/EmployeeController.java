package com.me.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.crud.bean.Employee;
import com.me.crud.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/emplist")
	public String queryEmployees(){
		
		List<Employee> elist = employeeService.getEmployees();
		
		return "list";
	}
	
}
