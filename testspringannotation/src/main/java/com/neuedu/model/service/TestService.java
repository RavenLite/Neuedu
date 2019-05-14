package com.neuedu.model.service;

import org.springframework.stereotype.Service;

@Service
public class TestService implements ITestService{

	public void test() {
		
		System.out.println("test service");
		
	}

}
