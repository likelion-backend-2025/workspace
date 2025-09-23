package net.likelion.lionboard.user.dao;


import net.likelion.lionboard.common.DBUtil;
import net.likelion.lionboard.user.dto.RoleDTO;
import net.likelion.lionboard.user.dto.UserRoleDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * UserRole 테이블에 대한 데이터 액세스 객체
 */
public class UserRoleDAO {

    /**
     * 사용자에게 권한을 부여합니다.
     */
    public int insertUserRole(UserRoleDTO userRole) {
        String sql = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userRole.getUserId());
            ps.setInt(2, userRole.getRoleId());

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("사용자 권한 부여 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    /**
     * 특정 사용자의 모든 권한을 조회합니다.
     */
    public List<RoleDTO> selectRolesByUserId(int userId) {
        String sql = """
                SELECT r.role_id, r.name 
                FROM role r 
                INNER JOIN user_role ur ON r.role_id = ur.role_id 
                WHERE ur.user_id = ?
                ORDER BY r.role_id
                """;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RoleDTO> roles = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                RoleDTO role = new RoleDTO();
                role.setRoleId(rs.getInt("role_id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }

        } catch (Exception e) {
            throw new RuntimeException("사용자 권한 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return roles;
    }

    /**
     * 특정 권한을 가진 모든 사용자를 조회합니다.
     */
    public List<Integer> selectUserIdsByRoleId(int roleId) {
        String sql = "SELECT user_id FROM user_role WHERE role_id = ? ORDER BY user_id";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> userIds = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();

            while (rs.next()) {
                userIds.add(rs.getInt("user_id"));
            }

        } catch (Exception e) {
            throw new RuntimeException("권한별 사용자 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return userIds;
    }

    /**
     * 사용자의 특정 권한을 제거합니다.
     */
    public int deleteUserRole(int userId, int roleId) {
        String sql = "DELETE FROM user_role WHERE user_id = ? AND role_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, roleId);

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("사용자 권한 제거 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    /**
     * 사용자의 모든 권한을 제거합니다.
     */
    public int deleteAllUserRoles(int userId) {
        String sql = "DELETE FROM user_role WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("사용자 권한 전체 제거 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    /**
     * 사용자가 특정 권한을 가지고 있는지 확인합니다.
     */
    public boolean hasRole(int userId, int roleId) {
        String sql = "SELECT COUNT(*) FROM user_role WHERE user_id = ? AND role_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (Exception e) {
            throw new RuntimeException("권한 확인 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
    /**
     * 특정 권한을 가진 사용자 수를 조회합니다.
     */
    public int getUserCountByRole(int roleId) {
        String sql = "SELECT COUNT(*) FROM user_role WHERE role_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (Exception e) {
            throw new RuntimeException("권한별 사용자 수 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

}
