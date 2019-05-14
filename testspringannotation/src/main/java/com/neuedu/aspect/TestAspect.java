package com.neuedu.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	
	@Before("execution(* com.neuedu.model.service.*.*(..))")
	public void test()
	{
		System.out.println("test");
	}
	
	

}
