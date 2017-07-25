package com.me.crud.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.me.crud.bean.Employee;
import com.me.crud.dao.EmployeeMapper;
import com.me.crud.service.EmployeeService;
import com.me.crud.util.Message;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//PUT是idempotent的，save操作显然不是幂等的？
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody Message save(@Valid Employee employee, BindingResult result){
		if(result.hasErrors()){
			return Message.failure().addObject("errormsg", result.getFieldErrors());
		}else{
			return employeeService.insert(employee);
		}
	}
	
	/**
	 * 删除员工
	 * @param ids
	 * 1
	 * 1-2-4-5
	 * @return
	 */
	@RequestMapping(value="/delete/{ids}",method=RequestMethod.DELETE)
	public @ResponseBody Message deleteByID(@PathVariable("ids")String ids){
		
		try{
			int id = Integer.parseInt(ids);
			return employeeService.deleteById(id);
		}catch(Exception e){//出现异常说明是批量删除....
			return employeeService.deleBatch(ids);
		}

	}
	
	
	/**
	 * 如果直接发送ajax=PUT形式的请求
	 * 封装的数据
	 * 除路径上的empId其他的全为Null
	 * 问题:
	 * 请求体中有数据:
	 * 但是Employee对象封装不上
	 *  update tbl_emp    where emp_id = 1014 
	 *  
	 *  原因:Tomcat:
	 *  1.将请求体中的数据,封装一个map.
	 *  2.request.getparameter("empName")就会从这个map中取值
	 *  3.SpringMVC封装POJO对象的时候
	 *  	会把POJO中中每个属性的值调用request.getparameter("email");
	 *  AJAX发送PUT请求引发的血案
	 *  PUT请求，请求体中的数据,request.getparameter("email),拿不到数据
	 *  Tomcat一看是PUT请求不会封装请求体中的数据为map,只有POST形式的请求才封装请求体为map
	 *  org.apache.catalina.connector.Request ; 
	 *  protected String parseBodyMethods = "POST"
	 *  if(!getConnector().isParseBodyMethod(getMethod())){
	 *  		success = true;
	 *  		return ;
	 *  }
	 *  
	 *   
	 *  
	 */
	
	/**解决方案 
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 1.配上HttpPutFormContentFilter
	 * 2.他的作用:将请求提中的数据解析包装成一个map. request被重新包装
	 * 3.request被重新包装:request.getparameter被()重写,就会从自己封装的map中取数据
	 * 员工更新方法
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public @ResponseBody Message updateEmployee(Employee e){
		employeeService.update(e);
		return Message.success();
	}
	
	/**
	 * paging query employees
	 * @param pn
	 * @return
	 */
	@RequestMapping("/list/{pn}")
	public @ResponseBody Message queryEmployees(@PathVariable(name="pn")Integer pn){
		
		PageHelper.startPage(pn,5);//起始页，每页记录数
		//从此处开始拦截sql，并加入分页功能
		List<Employee> elist = employeeService.getEmployees();
	
		PageInfo<Employee> pageInfo = new PageInfo<>(elist,4);//连续显示的页数
		
		return Message.success().addObject("pageInfo",pageInfo);
	}
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/info/{id}",method=RequestMethod.GET)
	public @ResponseBody Message getEmployee(@PathVariable(name="id")Integer id){
		
		Employee e = employeeService.getEmployee(id);
		
		return Message.success().addObject("emp",e);
	}
	

	
	/**
	 * checkEmail
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/email",method=RequestMethod.POST)
	public @ResponseBody Message checkEmail(@RequestParam("email") String email){
		
		if(employeeService.isEmailExist(email)){
			return Message.failure().addObject("msg", "邮箱不可用");
		}else{
			return Message.success();
		}
	}
	
	
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
/*	@ResponseBody
	@RequestMapping("/checkuser")
	public Message checkuser(@RequestParam("empName")String empName){
		//先判断用户名是否是合法的表达式
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		
		if(!(empName.matches(regx))){
			System.out.println("不可用");
			return Message.failure().addObject("va_msg","用户名必须是6-16位数字和字母的组合或者2-5位中文");
		}
		
		//数据库用户名重复校验
		boolean b =employeeService.checkUser(empName);
		if(b){
			return Message.success();
		}else{
			return Message.failure().addObject("va_msg", "用户名不可用");
		}
	}*/
	
}
