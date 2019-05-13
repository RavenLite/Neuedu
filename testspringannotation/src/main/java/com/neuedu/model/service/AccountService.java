package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.neuedu.model.dao.AccountDAO;
import com.neuedu.utils.DBUtils;

@Service
public class AccountService implements IAccountService{
	

	@Autowired
	private AccountDAO dao;
	
	public void transferMoney() throws SQLException
	{
		/*
		 * 1. get a connection conn
		 *    and put the conn in threadlocal(all methods in the same thread can share the connection)
		 * 2. try
		 * {
		 *     
		 *     dao.addMoney();
		 *     
		 *     dao.deductMoney();
		 *     
		 *     conn.commit;
		 * }
		 * catch(Exception e)
		 * {
		 *     conn.rollback; 
		 * }
		 * finally
		 * {
		 *    conn.close();
		 * }
		 * 
		 */
			
		/*try
		{*/
		
		  /* try
		   {*/
			   /*Connection conn = DBUtils.getConnection(); -> before advice*/
			   dao.deductMoney();
			   dao.addMoney();
		   /*}
		   finally
		   {
			   -> after advice
		   }*/
			
			//commit
		/*	DBUtils.commitConnection();  -> after returning advice
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//rollback
			DBUtils.rollbackConnection(); -> after throwing advice
		}
		*/
	}
	
	public static void main(String[] args) throws SQLException {
		
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		IAccountService service = (IAccountService)context.getBean("accountService");

		service.transferMoney();

	}

}
