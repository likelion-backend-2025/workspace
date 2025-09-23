package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateTest {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/liondb"; //DBMS들 마다 원하는 url 형식이 조금씩 다르더라.
        String user = "root";
        String password = "root1234";

        //1. DB접속
        Connection conn = DriverManager.getConnection(url,user,password);
//        System.out.println(conn);
        //2. 쿼리 작성
        String sql = "update dept set dname=? where deptno=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"총무부");
        ps.setInt(2,5);

        //3. 쿼리 실행
        int resutlCount = ps.executeUpdate();
        //4. 결과확인
        if(resutlCount>0){
            System.out.println("update success");
        }else {
            System.out.println("update fail");
        }

        ps.close();
        conn.close();

    }
}
