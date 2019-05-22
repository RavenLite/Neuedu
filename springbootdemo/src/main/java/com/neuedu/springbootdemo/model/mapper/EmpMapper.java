package com.neuedu.springbootdemo.model.mapper;

import com.neuedu.springbootdemo.model.po.Emp;

import java.util.List;

public interface EmpMapper {
	
	public List<Emp> getEmps(Emp condition);
	
	public void addEmp(Emp e);

}
