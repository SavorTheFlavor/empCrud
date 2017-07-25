package com.me.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.crud.bean.Employee;
import com.me.crud.dao.EmployeeMapper;
import com.me.crud.util.Message;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getEmployees(){
		return employeeMapper.selectWithDeparment(null);
	}
	
	public Message insert(Employee e){
		try{
			  employeeMapper.insertSelective(e);
		}catch(Exception exception){
			return Message.failure().addObject("errormsg", exception.getMessage());
		}
		return Message.success();
	}
	
	public Message deleteById(Integer id){
		employeeMapper.deleteByPrimaryKey(id);
		return Message.success();
	}
	
	/**
	 * ids: 1-2-3-4
	 * @param ids
	 * @return
	 */
	public Message deleBatch(String ids){
		List<Integer> idList = new ArrayList<>();
		String[] str_ids = ids.split("-");
		for (String id : str_ids) {
			idList.add(Integer.parseInt(id));
		}
		employeeMapper.deleteBatch(idList);
		return Message.success();
	}
	
	public Employee getEmployee(Integer id) {
		return employeeMapper.selectByPrimaryKey(id);
	}
	
	public int update(Employee record){
		return employeeMapper.updateByPrimaryKeySelective(record);
	}

	public boolean isEmailExist(String email) {
		Employee e = new Employee();
		e.setEmail(email);
		List<Employee> emps = employeeMapper.selectWithDeparment(e);
		if(emps == null || emps.size() <= 0){
			return false;
		}
		return true;
		
	}


	
	
	
}
