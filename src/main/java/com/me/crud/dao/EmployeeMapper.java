package com.me.crud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.me.crud.bean.Employee;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteBatch(List<Integer> ids);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);
    
    List<Employee> selectWithDeparment(Employee  employee);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}