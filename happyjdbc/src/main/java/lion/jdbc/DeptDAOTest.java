package lion.jdbc;

import java.util.List;

public class DeptDAOTest {
    public static void main(String[] args) {
        DeptDAO deptDAO = new DeptDAO();
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setDeptno(70);
        deptDTO.setDname("likelion");
        deptDTO.setLoc("LA");
//        boolean result = deptDAO.insertDept(deptDTO);
//        System.out.println(result);

//        int resultCount = deptDAO.deleteDept(60);
//        System.out.println(resultCount + "건 삭제했습니다.");


        DeptDAOwithDBUtil deptDAOwithDBUtil = new DeptDAOwithDBUtil();
//        List<DeptDTO> deptList = deptDAOwithDBUtil.getDeptList();
//
//        for (DeptDTO deptDTO1 : deptList) {
//            System.out.println(deptDTO1);
//        }

        DeptDTO resultDeptDTO = deptDAOwithDBUtil.getDept(10);

        System.out.println(resultDeptDTO);
    }
}
