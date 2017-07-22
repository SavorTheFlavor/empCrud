package com.me.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.crud.bean.Department;
import com.me.crud.service.DepartmentService;
import com.me.crud.util.Message;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@RequestMapping("/list")
	public @ResponseBody Message getDepartments(){
		
		List<Department> depts = departmentService.queryList();
		
		return Message.success().addObject("depts", depts);
	}
	
}
