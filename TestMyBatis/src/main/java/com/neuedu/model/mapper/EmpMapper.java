package com.neuedu.model.mapper;

import com.neuedu.model.po.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface EmpMapper {
    int addEmp(Emp emp);
    int addEmp1(HashMap<String, Object> map);

    void updateEmp(Emp emp);

    void deleteEmp(int empno);
    String getJobByEmpno(int empno);
    HashMap getInfoByEmpno(int empno);

    String getDeptByName(String dname);
    Emp getEmpInfoByEmpno(int empno);

    List<Emp> getEmpByConditions(Emp emp);
    List<Emp> getEmpByOneCondition(Emp emp);
    void updateEmpByCondition(Emp emp);
    List<Emp> getEmps(List<Integer> empnos);
    List<Emp> getEmps2(int[] empnos);
    List<Emp> getEmpByPage(@Param("index")int recordIndex, @Param("count")int recordCount);
}
