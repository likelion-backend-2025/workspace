package lion.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTest {
    public static void main(String[] args) {
       //1. 필요한 객체를 먼저 선언!!
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select deptno,dname,loc from dept";
        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while(rs.next()){
                System.out.print(rs.getInt(1)+"\t");
                System.out.print(rs.getString("dname")+"\t");
                System.out.println(rs.getString(3));
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
//            2. 선언했으면 닫아라!!
            DBUtil.close(conn,ps,rs);
        }
    }
}
