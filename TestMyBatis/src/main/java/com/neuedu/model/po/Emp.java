package com.neuedu.model.po;

import com.neuedu.model.mapper.EmpMapper;

import java.sql.Date;

//po stands for persist object
//pojo plain old java object
public class Emp {

    private int empno;
    private String ename;
    private String job;
    private int mgr;

    //what's the difference between util.date and sql.date?
    //sql.date extends utils.date
    //sql.data does't have time,util.date have time
    //in jdbc, if the database only store yyyy-mm-dd, you should use sql.date here
    //in jdbc, if the database also store hh-mm-ss, you should use sql.timestamp
	//in mybatis, you can use util.date
    private Date hiredate;
    private double sal;
    private double comm;
    private int deptno;
    public int getEmpno() {
        return empno;
    }
    public void setEmpno(int empno) {
        this.empno = empno;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public int getMgr() {
        return mgr;
    }
    public void setMgr(int mgr) {
        this.mgr = mgr;
    }
    public Date getHiredate() {
        return hiredate;
    }
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }
    public double getSal() {
        return sal;
    }
    public void setSal(double sal) {
        this.sal = sal;
    }
    public double getComm() {
        return comm;
    }
    public void setComm(double comm) {
        this.comm = comm;
    }
    public int getDeptno() {
        return deptno;
    }
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

}