package com.neuedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.model.po.Emp;
import com.neuedu.model.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {
		
	@Autowired
	private EmpService empService;
		
	//requestBody specify that get params from request body, instead of getting param from url
	
	@RequestMapping("/emps")
	public List<Emp> emps(@RequestBody Emp condition)
	{
		System.out.println(condition.getEmpno());
		System.out.println(condition.getEname());
		System.out.println(condition.getJob());
		//1.invoke service method
		// return the result
		return empService.getEmps(condition);		
	}

}
