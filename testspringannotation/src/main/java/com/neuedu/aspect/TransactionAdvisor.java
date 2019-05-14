package com.neuedu.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import com.neuedu.utils.DBUtils;

@Component
public class TransactionAdvisor implements MethodInterceptor {
	
	
	/**
	 * 
	 * because a advisor only have one advice, to specify the type of the advice, 
	 * you must implements different interface.
	 * 
	 * because we must implement a interface, this is called invasive design 
	 * which is actually not good.
	 *
	 */

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		
		DBUtils.getConnection();
		try
		{
			//call our business logic
			//dao.deductMoney();
			//dao.addMoney();
			arg0.proceed();
			
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
		
		return null;
	}
}
