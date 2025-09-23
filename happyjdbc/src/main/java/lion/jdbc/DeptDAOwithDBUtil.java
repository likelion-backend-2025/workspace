package lion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDAOwithDBUtil {
    public int deleteDept(int deptno) {
        String sql = "delete from dept where deptno=?";
        int resultCount = 0;

        try (
                //1. 접속 -- Connection
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                //2. 쿼리작성 -- Statement  --|> preparedStatement
        ) {
            ps.setInt(1, deptno);
            //3. 실행 -- executeUpdate
            resultCount = ps.executeUpdate();
            //4. 결과확인
//            System.out.println(resultCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultCount;
    }

    public boolean insertDept(DeptDTO deptDTO) {
        String sql = "insert into dept(deptno, dname, loc) values(?,?,?)";
        boolean resultFlag = false;
        try (
                Connection conn = DBUtil.getConnection();
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
    //1건 조회
    public DeptDTO getDept(int deptno){
        String sql = "SELECT deptno, dname,loc FROM dept WHERE deptno=?";
        DeptDTO deptDTO = null;
        ResultSet rs = null;
        try( Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setInt(1,deptno);
            rs = ps.executeQuery();
            if(rs.next()){
                //해당 데이터가 존재할 때만 가방을 생성함.
                deptDTO = new DeptDTO();
                deptDTO.setDeptno(rs.getInt(1));
                deptDTO.setDname(rs.getString("dname"));
                deptDTO.setLoc(rs.getString("loc"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            DBUtil.close(rs);
        }
        return deptDTO;
    }





    //조회
    public List<DeptDTO> getDeptList() {
        List<DeptDTO> deptList = new ArrayList<DeptDTO>();

        String sql = "select deptno,dname,loc from dept";
        try (   Connection conn =  DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet  rs = ps.executeQuery();
                ){
            while (rs.next()) {
                DeptDTO deptDTO = new DeptDTO();
                deptDTO.setDeptno(rs.getInt(1));
                deptDTO.setDname(rs.getString(2));
                deptDTO.setLoc(rs.getString(3));

                deptList.add(deptDTO);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return deptList;
    }

}
