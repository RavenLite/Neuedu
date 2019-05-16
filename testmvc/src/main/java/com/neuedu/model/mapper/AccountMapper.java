package com.neuedu.model.mapper;

import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
	
	public void addMoney(@Param("id")int id, @Param("money")double money);
	public void deductMoney(@Param("id")int id, @Param("money")double money);

}
