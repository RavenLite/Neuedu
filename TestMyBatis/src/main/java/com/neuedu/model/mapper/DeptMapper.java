package com.neuedu.model.mapper;

import com.neuedu.model.po.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    public Dept selectDept(int dn);
    public List<Dept> selectAllDept();
    public List<Dept> selectDeptByName(String dname);

    //have more than one argument
    public List<Dept> selectDeptByNameAndLoc(@Param("dname")String dname, @Param("loc")String loc);

    public Dept getDept(int deptno);
    public List<Dept> getDepts();
}