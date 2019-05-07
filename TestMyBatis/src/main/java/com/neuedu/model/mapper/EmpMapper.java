package com.neuedu.model.mapper;

import com.neuedu.model.po.Emp;

import java.util.HashMap;

public interface EmpMapper {
    int addEmp(Emp emp);
    int addEmp1(HashMap<String, Object> map);

    void updateEmp(Emp emp);

    void deleteEmp(int empno);
    String getJobByEmpno(int empno);
    HashMap getInfoByEmpno(int empno);

    String getDeptByName(String dname);
}
