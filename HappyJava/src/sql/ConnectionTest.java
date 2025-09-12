package sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) throws  Exception{
        // mysql이 8 버전부터는 생략해도 되는 부분..

        Class.forName("com.mysql.cj.jdbc.Driver");  //드라이버로딩.

        // 접속 확인!!
        //1. 접속
        String url= "jdbc:mysql://localhost:3306/liondb";
        String user = "root";
        String password = "root1234";
        Connection conn = null;  // 접속이 추상화 된 객체

        conn = DriverManager.getConnection(url, user, password);

        if(conn != null)
            System.out.println("^^한글");
        else
            System.out.println("-_-;;한글");

        conn.close();


    }
}
