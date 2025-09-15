package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertTest {
    public boolean insertDept(int deptno, String dname, String loc){


        String url = "jdbc:mysql://localhost:3306/liondb"; //DBMS들 마다 원하는 url 형식이 조금씩 다르더라.
        String user = "root";
        String password = "root1234";
        String sql = "insert into dept(deptno, dname, loc) values(?,?,?)";
        boolean resultFlag = false;
        try(
         Connection conn = DriverManager.getConnection(url,user,password);
        PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            //반드시 ? 에 값을 채워줘야함!!  -- 쿼리를 완성 시켜줘야함!!
            ps.setInt(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);

            //3. 실행!!
            int resultCount = ps.executeUpdate();

            if (resultCount == 1) {
                resultFlag = true;
                System.out.println("입력성공!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultFlag;
    }
    public static void main(String[] args) throws Exception{
        InsertTest insertTest = new InsertTest();
        boolean result = insertTest.insertDept(50, "lion", "seoul");
        System.out.println(result);


//        // 0. 드라이버로딩.  (MySQL 드라이버를 메모리에 올려준다) //생략가능!!
//
//
//        String url = "jdbc:mysql://localhost:3306/liondb"; //DBMS들 마다 원하는 url 형식이 조금씩 다르더라.
//        String user = "root";
//        String password = "root1234";
//
//        //1.접속 -- Connection
//        Connection conn = DriverManager.getConnection(url,user,password);
//
//        //2. 쿼리작성 -- Statement   , PreparedStaatement
////        쿼리문이 실행 될때 DBMS 가 알아들을 수 있게 번역(파싱) 된다.
////        같은쿼리가 또 들어오면 이미 번역 한 것을 사용한다.
////        insert into students(name) values('kang');
//        //        insert into students(name) values('hong');
//        //        insert into students(name) values('kim');
////        위의 쿼리는 각자 다른 쿼리로 인식!!  statement 는 값까지 들어간 완벽한 쿼로 실행 된다.
////        insert into student(name) values(?);  -- 여기까지를 번역함.
////        conn.set
//        String sql = "insert into dept(deptno, dname, loc) values(?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        //반드시 ? 에 값을 채워줘야함!!  -- 쿼리를 완성 시켜줘야함!!
//        ps.setInt(1,50);
//        ps.setString(2,"lion");
//        ps.setString(3,"seoul");
//
//        //3. 실행!!
//        int resultCount = ps.executeUpdate();
//
//        if(resultCount==1){
//            System.out.println("입력성공!!");
//        }
//
//        ps.close();
//        conn.close();
//

    }
}
