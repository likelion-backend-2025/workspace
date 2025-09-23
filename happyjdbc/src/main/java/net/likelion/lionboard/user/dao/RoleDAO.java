package net.likelion.lionboard.user.dao;


import net.likelion.lionboard.common.DBUtil;
import net.likelion.lionboard.user.dto.RoleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Role 테이블에 대한 데이터 액세스 객체
 */
class RoleDAO {

    /**
     * 모든 권한을 조회합니다.
     */
    public List<RoleDTO> selectAllRoles() {
        String sql = "SELECT role_id, name FROM role ORDER BY role_id";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RoleDTO> roles = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                RoleDTO role = new RoleDTO();
                role.setRoleId(rs.getInt("role_id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }

        } catch (Exception e) {
            throw new RuntimeException("권한 목록 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return roles;
    }

    /**
     * ID로 권한을 조회합니다.
     */
    public RoleDTO selectRoleById(int roleId) {
        String sql = "SELECT role_id, name FROM role WHERE role_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();

            if (rs.next()) {
                RoleDTO role = new RoleDTO();
                role.setRoleId(rs.getInt("role_id"));
                role.setName(rs.getString("name"));
                return role;
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException("권한 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * 새 권한을 등록합니다.
     */
    public int insertRole(RoleDTO role) {
        String sql = "INSERT INTO role (role_id, name) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, role.getRoleId());
            ps.setString(2, role.getName());

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("권한 등록 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps);
        }
    }
}
