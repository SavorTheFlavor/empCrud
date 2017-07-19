package com.me.empCrud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.me.crud.bean.Employee;
import com.me.crud.dao.DepartmentMapper;
import com.me.crud.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})//加载到ioc容器中
public class MapperTest {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Test
	public void selectTest(){
		Employee e = new Employee();
		e.setGender("f");
		System.out.println(employeeMapper.selectWithDeparment(null));
//		System.out.println(employeeMapper.selectByPrimaryKey(1).getDepartment().getName());
	}
	
	@Test
	public void insertTest(){
		Employee e = new Employee();
		e.setGender("f");
		e.setEmail("sadsada");
		e.setId(12);
		employeeMapper.insert(e);
		//employeeMapper.insertSelective(e);
	}

}
