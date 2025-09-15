package lion.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDAOwithClose {
    public int insert(DeptDTO deptDTO){
        int resultCount = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{

            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("insert into dept values(?,?,?)");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           DBUtil.close(conn,pstmt);
        }

        return resultCount;
    }

    public List<DeptDTO> getDeptList() {
        List<DeptDTO> deptList = new ArrayList<DeptDTO>();
        //1. 필요한 객체를 먼저 선언!!
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select deptno,dname,loc from dept";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.print(rs.getInt(1)+"\t");
//                System.out.print(rs.getString("dname")+"\t");
//                System.out.println(rs.getString(3));
                DeptDTO deptDTO = new DeptDTO();
                deptDTO.setDeptno(rs.getInt(1));
                deptDTO.setDname(rs.getString(2));
                deptDTO.setLoc(rs.getString(3));

                deptList.add(deptDTO);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            2. 선언했으면 닫아라!!
            DBUtil.close(conn, ps, rs);
        }
        return deptList;
    }
}
