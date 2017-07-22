package com.me.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.crud.bean.Department;
import com.me.crud.bean.Employee;
import com.me.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> queryList(){
		return departmentMapper.selectByExample(null);
	}
	
}
