package com.neuedu.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.neuedu.utils.DBUtils;

@Component
/*@Aspect*/
public class TransactionAspect{
	
/*	@Before("execution(* com.neuedu.model.service.AccountService.*(..))")
	public void getConnection()
	{
		DBUtils.getConnection();
	}
	
	@AfterReturning("execution(* com.neuedu.model.service.AccountService.*(..))")
	public void commitConnection()
	{
		DBUtils.commitConnection();
		DBUtils.closeConnection();
		
	}
	
	@AfterThrowing("execution(* com.neuedu.model.service.AccountService.*(..))")
	public void rollbackConnection()
	{
		DBUtils.rollbackConnection();
		DBUtils.closeConnection();
	}*/
	
	/*@After("execution(* com.neuedu.model.service.AccountService.*(..))")
	public void closeConnection()
	{
		
	}*/
	
	/**
	 * 
	 *around advice is very powerfully, it allows you controll your proxy object all by your self.
	 *
	 *
	 */
	
	/*@Around("execution(* com.neuedu.model.service.AccountService.*(..))")*/
	public void process(ProceedingJoinPoint pjp) throws Throwable
	{
		DBUtils.getConnection();
		try
		{
			//call our business logic
			//dao.deductMoney();
			//dao.addMoney();
			pjp.proceed();
			
			DBUtils.commitConnection();
		}
		catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			DBUtils.rollbackConnection();
			
			throw e;
		}
		finally
		{
			DBUtils.closeConnection();
		}
	}
    
}
