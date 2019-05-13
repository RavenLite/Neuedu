package com.neuedu.model.service;

import org.springframework.stereotype.Service;

@Service
public class EmpService {
	
	public void addEmp()
	{
		//business logic
		System.out.println("add a emp");
		//make a exception on purpose
		Integer.parseInt("s");
		
	}
	
	public void deleteEmp()
	{	
		//business logic
		System.out.println("delete a emp");		
	}
	
	public void updateEmp()
	{
		//business logic
		System.out.println("update a emp");
	}

}
