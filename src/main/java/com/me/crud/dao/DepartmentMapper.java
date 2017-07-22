package com.me.crud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.me.crud.bean.Department;


@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);
    
    List<Department> selectByExample(Department department);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}