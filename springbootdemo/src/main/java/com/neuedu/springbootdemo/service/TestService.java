package com.neuedu.springbootdemo.service;

import java.util.List;

import com.neuedu.springbootdemo.model.mapper.EmpMapper;
import com.neuedu.springbootdemo.model.po.Emp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TestService {
	
	@Resource
	private EmpMapper empMapper;
	
	public void test()
	{
		System.out.println(empMapper);
		System.out.println("test");
	}
	
	public List<Emp> getEmps(Emp condition)
	{
		return empMapper.getEmps(condition);
	}
	
	@Transactional
	public void addEmp(Emp e)throws Exception
	{		
		empMapper.addEmp(e);
	}

}
