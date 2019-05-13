package com.neuedu.aspect;

import java.sql.Connection;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.neuedu.utils.DBUtils;

@Component
@Aspect
public class TransactionAspect {
	
	@Before("execution(* com.neuedu.model.service.AccountService.*(..))")
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
	}
	
	/*@After("execution(* com.neuedu.model.service.AccountService.*(..))")
	public void closeConnection()
	{
		DBUtils.closeConnection();		
	}*/

}
