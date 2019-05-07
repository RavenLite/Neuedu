import com.neuedu.model.mapper.EmpMapper;
import com.neuedu.model.po.Emp;
import com.neuedu.model.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.HashMap;

public class TestMyBatisEmp {

    public static void main(String[] args) {
        getInfo();
    }

    public static void insert1() {
        //get SqlSession
        SqlSession session = DBUtils.getInstance();

        //get mapper
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        //create a instance of Emp
        Emp emp = new Emp();
        emp.setEname("feiyy");
        emp.setMgr(7369);
        emp.setJob("teacher");
        //how to get a sql.Date
        //System.currentTimeMillis() returns milliseconds between now and 1970-1-1 0:0:0
        emp.setHiredate(new Date(System.currentTimeMillis()));
        emp.setSal(8888.88);
        emp.setComm(6666.66);
        emp.setDeptno(10);
        mapper.addEmp(emp);

        System.out.println(emp.getEmpno());
        //to commit session
        session.commit();

    }

    public static void insert2() {
        //get SqlSession
        SqlSession session = DBUtils.getInstance();

        //get mapper
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        //create a hashmap
        HashMap<String, Object> map = new HashMap<>();
        map.put("ename", "feiyy");
        map.put("job", "teacher");
        map.put("mgr", 7369);
        map.put("hiredate", new Date(System.currentTimeMillis()));
        map.put("sal", 8888.88);
        map.put("comm", 6666.66);
        map.put("deptno", 10);


        mapper.addEmp1(map);
        System.out.println(map.get("empno"));

        //to commit session
        session.commit();
    }

    public static void getJob() {
        SqlSession session = DBUtils.getInstance();
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        String job = mapper.getJobByEmpno(7369);
        System.out.println(job);
    }
    public static void updateEmp()
    {
        SqlSession session = DBUtils.getInstance();
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        Emp e = new Emp();
        e.setEmpno(8888);
        e.setSal(9999.66);
        e.setComm(1888);

        mapper.updateEmp(e);

        session.commit();
    }

    public static void deleteEmp()
    {
        SqlSession session = DBUtils.getInstance();
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        mapper.deleteEmp(8891);
        session.commit();
    }

    public static void getInfo() {
        SqlSession session = DBUtils.getInstance();
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        HashMap info = mapper.getInfoByEmpno(7369);
        System.out.println(info.get("ename") + "\t" + info.get("job"));
    }

}