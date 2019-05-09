import java.util.List;

import com.neuedu.model.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import com.neuedu.model.mapper.DeptMapper;
import com.neuedu.model.mapper.EmpMapper;
import com.neuedu.model.mapper.ScoresMapper;
import com.neuedu.model.po.Dept;
import com.neuedu.model.po.Emp;
import com.neuedu.model.po.Scores;
import com.neuedu.model.po.Student;

/**
 * @author Raven
 */
public class TestMyBatisEntityRelation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		getEmpInfo();
//		getDeptInfo();
		//getDepts();
		//getEmpInfo();
		getStudent();
	}
	
	public static void getEmpInfo()
	{
		//1.get SqlSession
		SqlSession session = DBUtils.getInstance();
		//2.get Mapper
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		Emp e = mapper.getEmpInfoByEmpno(7369);
		System.out.println(e.getEmpno()+"\t"+e.getEname()+"\t"+e.getD().getDeptno()+"\t"+e.getD().getDname()+"\t"+e.getD().getLoc());
	}
	
	public static void getDeptInfo()
	{
		//1.get SqlSession
		SqlSession session = DBUtils.getInstance();
		
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		Dept d = mapper.getDept(10);
		
		System.out.println(d.getDeptno()+"\t"+d.getDname()+"\t"+d.getLoc());
		
		//get all employees of this department
		List<Emp> list = d.getEmps();
		for(Emp e:list)
		{
			System.out.println(e.getEmpno()+"\t"+e.getEname());
		}
	}
	
	public static void getDepts()
	{
		//1.get SqlSession
		SqlSession session = DBUtils.getInstance();
		
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		List<Dept> ds = mapper.getDepts();
		for(Dept d:ds)
		{
			System.out.println(d.getDeptno()+"\t"+d.getDname()+"\t"+d.getLoc());
			
			//get all employees of this department
			List<Emp> list = d.getEmps();
			for(Emp e:list)
			{
				System.out.println(e.getEmpno()+"\t"+e.getEname());
			}
		}
	}
	
	public static void getStudent()
	{
		//1.get SqlSession
		SqlSession session = DBUtils.getInstance();
		
		//2. Mapper
		ScoresMapper mapper = session.getMapper(ScoresMapper.class);
		
		Student s = mapper.getStudentById(1);
		
		//output basic information for s
		System.out.println(s.getSid()+"\t"+s.getSname());
		
		List<Scores> scores = s.getScores();
		for(Scores item :scores)
		{
			System.out.println(item.getC().getCid()+"\t"+item.getC().getCname()+"\t"+item.getScore()+"\t"+item.getS().getSname());
		}
	}

}
