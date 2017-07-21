package com.me.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.me.crud.bean.Employee;
import com.me.crud.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/list/{pn}")
	public String queryEmployees(@PathVariable(name="pn")Integer pn, Model model){
		
		PageHelper.startPage(pn,5);//起始页，每页记录数
		//从此处开始拦截sql，并加入分页功能
		List<Employee> elist = employeeService.getEmployees();
	
		PageInfo<Employee> pageInfo = new PageInfo<>(elist,4);//连续显示的页数
		model.addAttribute("pageInfo", pageInfo);
		
		return "list";
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	
}
