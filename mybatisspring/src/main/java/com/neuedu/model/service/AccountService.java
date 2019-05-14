package com.neuedu.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.model.mapper.AccountMapper;

@Service
public class AccountService {
	
	//Mapper should be created by Spring
	/**
	 * in order for spring to create a Mapper class, it must have controll of the database, 
	 * 
	 * have controll of Mybatis SqlSessionFactory, and also have control of SqlSession

	 */
	
	@Autowired
	private AccountMapper accountMapper; 
	
	/**
	 * expect Spring to control transaction management
	 */
	
	@Transactional
	public void transferMoney()
	{
		accountMapper.deductMoney(1, 100);
		accountMapper.addMoney(2, 100);
	}
	
	@Transactional
	public void addMoney()
	{
		accountMapper.addMoney(1, 100);		
		accountMapper.addMoney(2, 100);
	}

	@Transactional
	public void changeMoney() {
		accountMapper.changeMoney(2, 1000);
	}
}
