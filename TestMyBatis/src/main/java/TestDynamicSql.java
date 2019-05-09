import com.neuedu.model.mapper.EmpMapper;
import com.neuedu.model.po.Emp;
import com.neuedu.model.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Raven
 */
public class TestDynamicSql {
    public static void main(String[] args) {
        queryByPage();
    }

    public static void getEmpByConditions() {
        //get SqlSession
        SqlSession session = DBUtils.getInstance();

        //get mapper
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        Emp emp = new Emp();
        emp.setEname("SMITH");
        List<Emp> emps = mapper.getEmpByConditions(emp);

        Iterator<Emp> it = emps.iterator();
        while (it.hasNext()) {
            Emp item = it.next();
            //output its content
            System.out.println(item.getEname() + "\t" + item.getEmpno());
        }
    }

    public static void getEmpByOneCondition() {
        //get SqlSession
        SqlSession session = DBUtils.getInstance();

        //get mapper
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        Emp emp = new Emp();
        emp.setEname("SMITH");
        List<Emp> emps = mapper.getEmpByOneCondition(emp);

        Iterator<Emp> it = emps.iterator();
        while (it.hasNext()) {
            Emp item = it.next();
            //output its content
            System.out.println(item.getEname() + "\t" + item.getEmpno());
        }
    }

    public static void updateEmpByCondition() {
        //get SqlSession
        SqlSession session = DBUtils.getInstance();

        //get mapper
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        Emp emp = new Emp();
        emp.setEmpno(8888);
        emp.setEname("Raven");
        emp.setJob("CLERK");
        mapper.updateEmpByCondition(emp);

        session.commit();
    }

    public static void getEmps() {
        SqlSession session = DBUtils.getInstance();
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        List<Integer> list = new ArrayList<>();
        list.add(7566);
        list.add(7369);
        list.add(8888);

        List<Emp> emps = mapper.getEmps(list);
        for(Emp e : emps) {
            System.out.println(e.getEmpno() + "\t" + e.getEname() + "\t" + e.getJob());
        }
    }

    public static void getEmps2() {
        SqlSession session = DBUtils.getInstance();
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        int[] arr = {7566, 7369, 8888};

        List<Emp> emps = mapper.getEmps2(arr);
        for(Emp e : emps) {
            System.out.println(e.getEmpno() + "\t" + e.getEname() + "\t" + e.getJob());
        }
    }

    public static void queryByPage() {
        SqlSession session = DBUtils.getInstance();
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        List<Emp> emps = mapper.getEmpByPage(0, 10);
        for(Emp e : emps) {
            System.out.println(e.getEmpno() + "\t" + e.getEname() + "\t" + e.getJob());
        }

        // this time, get data from cache
        // by default, data will be cached and shared in one session
        // if you create two different sessions, data can not be shared
        List<Emp> emps2 = mapper.getEmpByPage(0, 10);
        for(Emp e : emps) {
            System.out.println(e.getEmpno() + "\t" + e.getEname() + "\t" + e.getJob());
        }
    }
}
