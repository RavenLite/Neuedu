import com.neuedu.model.mapper.DeptMapper;
import com.neuedu.model.po.Dept;
import com.neuedu.model.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Iterator;
import java.util.List;

public class TestMyBatis {

    public static void main(String[] args) {
        SqlSession session = DBUtils.getInstance();

        // 3. Run a SQL
        // 3.1 normal
        // old version
        // Dept d = session.selectOne("com/neuedu/model/DeptMapper.java.selectDept", 20);
        // System.out.println(d.getDeptno()+"\t"+d.getDname()+"\t"+d.getLoc());

        // new version
        DeptMapper mapper = session.getMapper(DeptMapper.class);
        Dept d = mapper.selectDept(20);
        System.out.println(d.getDeptno() + "\t" + d.getDname() + "\t" + d.getLoc());

        // 3.2 many return objects
        List<Dept> ds = mapper.selectAllDept();
        //to iterate this list
        Iterator<Dept> it = ds.iterator();
        while (it.hasNext()) {
            Dept item = it.next();
            //output its content
            System.out.println(item.getDeptno() + "\t" + item.getDname() + "\t" + item.getLoc());
        }

        // 3.3 keyword -> like
        List<Dept> ds2 = mapper.selectDeptByName("SEARCH");
        Iterator<Dept> it2 = ds.iterator();
        while (it2.hasNext()) {
            Dept item = it2.next();
            //output its content
            System.out.println(item.getDeptno() + "\t" + item.getDname() + "\t" + item.getLoc());
        }

        // 3.4 multiple parameters
        List<Dept> ds3 = mapper.selectDeptByNameAndLoc("RESEARCH", "DALLAS");
        //to iterate this list
        Iterator<Dept> it3 = ds3.iterator();
        while (it3.hasNext()) {
            Dept item = it.next();
            //output its content
            System.out.println(item.getDeptno() + "\t" + item.getDname() + "\t" + item.getLoc());
        }

    }
}