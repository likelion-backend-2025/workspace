package net.likelion.lionboard.user.dao;


import net.likelion.lionboard.common.DBUtil;
import net.likelion.lionboard.user.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User 테이블에 대한 데이터 액세스 객체
 */
public class UserDAO {

    /**
     * 새 사용자를 등록합니다.
     */
    public int insertUser(UserDTO user) {
        String sql = "INSERT INTO user (name, password, email, regdate) VALUES (?, ?, ?, NOW())";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("사용자 등록에 실패했습니다.");
            }

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("생성된 ID를 가져올 수 없습니다.");
            }

        } catch (Exception e) {
            throw new RuntimeException("사용자 등록 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * 사용자명과 비밀번호로 로그인을 확인합니다.
     */
    public UserDTO selectUserByUsernameAndPassword(String name, String password) {
        String sql = "SELECT user_id, name, password, email, regdate FROM user WHERE name = ? AND password = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));

                Timestamp timestamp = rs.getTimestamp("regdate");
                if (timestamp != null) {
                    user.setRegdate(timestamp.toLocalDateTime());
                }

                return user;
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException("사용자 로그인 확인 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * ID로 사용자를 조회합니다.
     */
    public UserDTO selectUserById(int userId) {
        String sql = "SELECT user_id, name, password, email, regdate FROM user WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));

                Timestamp timestamp = rs.getTimestamp("regdate");
                if (timestamp != null) {
                    user.setRegdate(timestamp.toLocalDateTime());
                }

                return user;
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException("사용자 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * 사용자명 중복 체크
     */
    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM user WHERE name = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (Exception e) {
            throw new RuntimeException("사용자명 중복 확인 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * 전체 사용자 조회
     */
    public List<UserDTO> selectAllUsers() {
        String sql = "SELECT user_id, email, name, password, regdate FROM user ORDER BY user_id";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UserDTO> users = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));

                Timestamp timestamp = rs.getTimestamp("regdate");
                if (timestamp != null) {
                    user.setRegdate(timestamp.toLocalDateTime());
                }

                users.add(user);
            }

        } catch (Exception e) {
            throw new RuntimeException("전체 사용자 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return users;
    }

    /**
     * 전체 사용자 수 조회
     */
    public int getTotalUserCount() {
        String sql = "SELECT COUNT(*) FROM user";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (Exception e) {
            throw new RuntimeException("사용자 수 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }


    /**
     * 사용자의 권한 목록을 조회합니다.
     */
    public List<String> selectUserRoles(int userId) {
        String sql = """
            SELECT r.name 
            FROM role r 
            JOIN user_role ur ON r.role_id = ur.role_id 
            WHERE ur.user_id = ?
            ORDER BY r.role_id
            """;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> roles = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                roles.add(rs.getString("name"));
            }

        } catch (Exception e) {
            throw new RuntimeException("사용자 권한 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return roles;
    }
}
