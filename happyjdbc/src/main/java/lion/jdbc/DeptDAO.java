package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeptDAO {
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

    public boolean insertDept(DeptDTO deptDTO){
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
            ps.setInt(1, deptDTO.getDeptno());
            ps.setString(2, deptDTO.getDname());
            ps.setString(3, deptDTO.getLoc());

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
}
