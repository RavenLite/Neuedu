package com.neuedu.model.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.model.mapper.EmpMapper;
import com.neuedu.model.po.Emp;

@Service
public class EmpService {
	
	@Autowired
	private EmpMapper empMapper;
	
	@Transactional
	public List<Emp> getEmps(Emp condition)
	{
		List<Emp> ret =  empMapper.getEmps(condition);
		for(Emp e:ret)
		{
			//java.sql.Date -> String
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			e.setHiredatestr(sdf.format(e.getHiredate()));
		}
		return ret;
	}
	

}
