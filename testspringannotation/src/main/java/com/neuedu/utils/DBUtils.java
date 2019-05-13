package com.neuedu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * this class is used to manage database connection
 *
 */
public class DBUtils {
	
	private static ThreadLocal<Connection> tl = new ThreadLocal<>();
	
	//this static block automatically runs when DBUtils is loaded into memory
	static
	{
		//load the database connector
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		//1. if there is connection inside threadlocal
		//threadlocal.get(), threadlocal.set()
		if(tl.get()==null)
		{
			//create a connection, and put it in threadlocal
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "root");
			
				//set autocommit = false;
				conn.setAutoCommit(false);
				
			    tl.set(conn);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		return tl.get();
	}
	
	public static void commitConnection()
	{
		try {
			tl.get().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollbackConnection()
	{
		try {
			tl.get().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConnection()
	{
		//first close the connection
		try {
			tl.get().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//remove it from threadlocal
		//tl.remove();
		
		
	}
	
	

}
