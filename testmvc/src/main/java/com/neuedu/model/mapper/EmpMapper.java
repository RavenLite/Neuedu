package com.neuedu.model.mapper;

import java.util.List;

import com.neuedu.model.po.Emp;

public interface EmpMapper {
	
	public List<Emp> getEmps(Emp condition);

}
