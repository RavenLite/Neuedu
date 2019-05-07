import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import com.neuedu.model.mapper.DeptMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.neuedu.model.po.Dept;

public class TestMyBatis {

    public static void main(String[] args) {


        String resource = "mybatis-config.xml";
        InputStream inputStream;
        SqlSession session = null;
        try {
            // 1. Create a sqlsessionFactory
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);

            // org.apache.ibatis.session.defaults.DefaultSqlSessionFactory@35bbe5e8
            // 35bbe5e8 this is hashcode for this object
            // (this hashcode is calculated according to object memory address)
            System.out.println(sqlSessionFactory);

            // 2. Use SqlSessionFactory to get SqlSession(equal to connection)
            session = sqlSessionFactory.openSession();
            System.out.println(session);

            // 3. Run a SQL
            // 3.1 normal
            // old version
            // Dept d = session.selectOne("com/neuedu/model/DeptMapper.java.selectDept", 20);
            // System.out.println(d.getDeptno()+"\t"+d.getDname()+"\t"+d.getLoc());

            // new version
            DeptMapper mapper = session.getMapper(DeptMapper.class);
            Dept d = mapper.selectDept(20);
            System.out.println(d.getDeptno()+"\t"+d.getDname()+"\t"+d.getLoc());

            // 3.2 many return objects
            List<Dept> ds = mapper.selectAllDept();
            //to iterate this list
            Iterator<Dept> it = ds.iterator();
            while(it.hasNext())
            {
                Dept item = it.next();
                //output its content
                System.out.println(item.getDeptno()+"\t"+item.getDname()+"\t"+item.getLoc());
            }

            // 3.3 keyword -> like
            List<Dept> ds2 = mapper.selectDeptByName("SEARCH");
            Iterator<Dept> it2 = ds.iterator();
            while(it2.hasNext())
            {
                Dept item = it2.next();
                //output its content
                System.out.println(item.getDeptno()+"\t"+item.getDname()+"\t"+item.getLoc());
            }

            // 3.4 multiple parameters
            List<Dept> ds3 = mapper.selectDeptByNameAndLoc("RESEARCH", "DALLAS");
            //to iterate this list
            Iterator<Dept> it3 = ds3.iterator();
            while(it3.hasNext())
            {
                Dept item = it.next();
                //output its content
                System.out.println(item.getDeptno()+"\t"+item.getDname()+"\t"+item.getLoc());
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}