package com.me.crud.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

@Component
public class Employee {
    private Integer id;

    @Pattern(regexp="(^[a-zA-Z0-9_-]{2,16}$)|(^[\u2E80-\u9FFF]{1,5}[0-9a-zA-Z]*)",message="用户名不合法")
    private String name;

    private String gender;

    @Email(message="格式不对")
    private String email;

    private Integer departmentId;
    
    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", departmentId="
				+ departmentId + ", department=" + department + "]";
	}
    
	
    
}