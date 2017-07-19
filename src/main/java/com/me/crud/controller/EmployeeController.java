package com.me.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.me.crud.bean.Employee;
import com.me.crud.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/emplist")
	public String queryEmployees(@RequestParam(name="pn",defaultValue="1")Integer pn, Model model){
		
		PageHelper.startPage(pn,5);//起始页，数量
		//从此处开始拦截sql，并加入分页功能
		List<Employee> elist = employeeService.getEmployees();
	
		PageInfo<Employee> pageInfo = new PageInfo<>(elist,4);//连续显示的页数
		model.addAttribute("pageInfo", pageInfo);
		
		return "list";
	}
	
}
