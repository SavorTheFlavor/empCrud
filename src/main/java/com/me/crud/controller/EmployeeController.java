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
	
	@RequestMapping("/list/{pn}")
	public @ResponseBody Message queryEmployees(@PathVariable(name="pn")Integer pn, Model model){
		
		PageHelper.startPage(pn,5);//起始页，每页记录数
		//从此处开始拦截sql，并加入分页功能
		List<Employee> elist = employeeService.getEmployees();
	
		PageInfo<Employee> pageInfo = new PageInfo<>(elist,4);//连续显示的页数
		
		return Message.success().addObject("pageInfo",pageInfo);
	}
	
	
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
