package com.me.empCrud;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
		PageHelper.startPage(2, 3);
		List<Employee> ll = employeeMapper.selectWithDeparment(null);
		PageInfo<Employee> pageInfo = new PageInfo<>(ll,3);
		System.out.println("total:"+pageInfo.getTotal());
		System.out.println("emps:"+pageInfo.getList());
//		System.out.println(employeeMapper.selectByPrimaryKey(1).getDepartment().getName());
	}
	
	@Test
	public void insertTest(){
		Employee e = new Employee();
		for (int i = 0; i < 20; i++) {
			e.setName("biu"+i);
			e.setGender("f");
			e.setEmail("sadsada");
			e.setId(30+i);
			employeeMapper.insertSelective(e);
		}

		
		//employeeMapper.insertSelective(e);
	}

}
