package com.neuedu.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
/*@Aspect*/
public class LogAspect {
	
	/**
	 * AOP: many concepts, 
	 * 
	 * advice(): before, after(finally), after-throwing(works in case of exception), after-returning(works when this is no exception), around(in spring, it is always a method in a aspect)
	 * 
	 * after-throwing and after-returning, there are mutually exclusive
	 * 
	 * 
	 * pointcut():specifies which methods the advice should be weaved
	 * 
	 * weaving(֯):the behavior of cutting into other methods
	 * 
	 * 
	 */
	
	/*aspectJ weaving syntax
	execution(* com.neuedu.model.service.*.*(..)) 
	stands for the before method will be weaved into all methods under classes of com.neuedu.model.service 
	*/
	/*@Before("execution(* com.neuedu.model.service.*.*(..))")*/
	public void before()
	{
		//before the method executes:
		System.out.println("methods before");
	}
	
	/*@After("execution(* com.neuedu.model.service.*.*(..))")*/
	public void after()
	{
		//after the method executes:
		System.out.println("methods after");
	}
	
	
	/*@AfterThrowing("execution(* com.neuedu.model.service.*.*(..))")*/
	public void afterthrowing()
	{
		System.out.println("methods exception");
	}
	
	
	/*@AfterReturning("execution(* com.neuedu.model.service.*.*(..))")*/
	public void afterreturnning()
	{
		System.out.println("methods runs without exception");
	}
	
	

}
