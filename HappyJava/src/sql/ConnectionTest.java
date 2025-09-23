package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) throws Exception {
        //자바프로그램도 DBMS 입장에서 클라이언트이다.
//        1. DB 접속해야함.  Connection -- 접속이 추상화된 객체.

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/liondb"; //DBMS들 마다 원하는 url 형식이 조금씩 다르더라.
        String user = "root";
        String password = "root1234";
        connection = DriverManager.getConnection(url,user,password);

        if (connection != null) {
            System.out.println("^^");
        }else  {
            System.out.println("-_-;;");
        }
    }
}
