import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.model.po.Dept;

public class TestJDBC {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            //1. Load driver into memory
            Class.forName("com.mysql.jdbc.Driver");
            //2. Get connection from database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "root");
            //3. Create preparedStatement to write a Sql
            stat = conn.prepareStatement("select * from dept where deptno = ?");
            //3.2 replace all ? with actual value
            stat.setInt(1, 10);
            //4. execute sql
            rs = stat.executeQuery();
            if(rs.next())
            {
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                //wrap these datas into a Dept object
                Dept d = new Dept();
                d.setDeptno(deptno);
                d.setDname(dname);
                d.setLoc(loc);

                System.out.println(d.getDeptno()+"\t"+d.getDname()+"\t"+d.getLoc());
            }
            //5. get the result
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if(rs!=null)
            {
                try {
                    rs.close();// it throws nullpointerexception
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(stat !=null)
            {
                try {
                    stat.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(conn !=null)
            {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }


    }

}
