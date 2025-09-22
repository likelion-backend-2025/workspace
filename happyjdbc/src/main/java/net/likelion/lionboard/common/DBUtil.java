package net.likelion.lionboard.common;

import java.sql.*;

public class DBUtil {
    //할일은 뭔가요?  어떤 기능???
    //커넥션 객체를 얻어와야 해요.
    //접속
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/likedb"; //DBMS들 마다 원하는 url 형식이 조금씩 다르더라.
        String user = "root";
        String password = "root1234";

        return DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection( String user, String password) {
        return null;
    }

    public static void close(Connection conn) {

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt) {
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        close(conn);
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        close(conn, pstmt);
    }

    public static void close(ResultSet rs) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(DBUtil.getConnection());
    }
}
