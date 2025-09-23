package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteTest {
    //접근제한자 리턴타입 메서드명(매개변수...)
    public int deleteDept(int deptno){
        String url = "jdbc:mysql://localhost:3306/liondb"; //DBMS들 마다 원하는 url 형식이 조금씩 다르더라.
        String user = "root";
        String password = "root1234";

        String sql = "delete from dept where deptno=?";
        int resultCount = 0;

        try(
                //1. 접속 -- Connection
                Connection conn = DriverManager.getConnection(url,user,password);
                PreparedStatement ps = conn.prepareStatement(sql);
                //2. 쿼리작성 -- Statement  --|> preparedStatement
        ) {
            ps.setInt(1,deptno);
            //3. 실행 -- executeUpdate
            resultCount = ps.executeUpdate();
            //4. 결과확인
//            System.out.println(resultCount);
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultCount;
    }

    public static void main(String[] args) {
        DeleteTest deleteTest = new DeleteTest();
        int resultCount = deleteTest.deleteDept(50);
        System.out.println(resultCount);


//        String url = "jdbc:mysql://localhost:3306/liondb"; //DBMS들 마다 원하는 url 형식이 조금씩 다르더라.
//        String user = "root";
//        String password = "root1234";
//
//        String sql = "delete from dept where deptno=?";
//
//        try(
//                //1. 접속 -- Connection
//                Connection conn = DriverManager.getConnection(url,user,password);
//                PreparedStatement ps = conn.prepareStatement(sql);
//                //2. 쿼리작성 -- Statement  --|> preparedStatement
//        ) {
//            ps.setInt(1,5);
//            //3. 실행 -- executeUpdate
//            int resultCount = ps.executeUpdate();
//            //4. 결과확인
//            System.out.println(resultCount);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
}
