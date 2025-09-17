package net.likelion.lionboard.board.controller;


import net.likelion.lionboard.common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 콘솔 게시판 시스템 메인 클래스
 */
public class ConsoleBoardSystem {

    public static void main(String[] args) {
        // 데이터베이스 초기화 (선택사항)
        initializeDatabase();

        // 컨트롤러 시작
        BoardController controller = new BoardController();
        controller.start();
    }

    /**
     * 데이터베이스 초기 설정
     */
    private static void initializeDatabase() {
        try {
            System.out.println("🔄 데이터베이스 초기화 중...");

            // 기본 테이블 생성 및 초기 데이터 삽입
            createTablesIfNotExists();
            insertInitialRoles();
            insertInitialAdminUser();

            System.out.println("✅ 데이터베이스 초기화 완료!");

        } catch (Exception e) {
            System.err.println("⚠️ 데이터베이스 초기화 중 오류 (무시하고 진행): " + e.getMessage());
        }
    }

    private static void createTablesIfNotExists() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();

            // User 테이블 생성
            String createUserTable = """
                CREATE TABLE IF NOT EXISTS user (
                    user_id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(20) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL,
                    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;
            ps = conn.prepareStatement(createUserTable);
            ps.executeUpdate();
            ps.close();

            // Role 테이블 생성
            String createRoleTable = """
                CREATE TABLE IF NOT EXISTS role (
                    role_id INT PRIMARY KEY,
                    name VARCHAR(20) NOT NULL
                )
                """;
            ps = conn.prepareStatement(createRoleTable);
            ps.executeUpdate();
            ps.close();

            // UserRole 테이블 생성
            String createUserRoleTable = """
                CREATE TABLE IF NOT EXISTS user_role (
                    user_id INT,
                    role_id INT,
                    PRIMARY KEY(user_id, role_id),
                    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
                    FOREIGN KEY (role_id) REFERENCES role(role_id)
                )
                """;
            ps = conn.prepareStatement(createUserRoleTable);
            ps.executeUpdate();
            ps.close();

            // Board 테이블 생성
            String createBoardTable = """
                CREATE TABLE IF NOT EXISTS board (
                    board_id INT PRIMARY KEY AUTO_INCREMENT,
                    title VARCHAR(100) NOT NULL,
                    content TEXT NULL,
                    user_id INT NOT NULL,
                    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    view_cnt INT DEFAULT 0,
                    FOREIGN KEY (user_id) REFERENCES user(user_id),
                    INDEX idx_regdate (regdate DESC)
                )
                """;
            ps = conn.prepareStatement(createBoardTable);
            ps.executeUpdate();

        } finally {
            DBUtil.close(conn, ps);
        }
    }

    private static void insertInitialRoles() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();

            // 기존 권한 데이터 확인
            String checkSql = "SELECT COUNT(*) FROM role";
            ps = conn.prepareStatement(checkSql);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                ps.close();
                rs.close();

                // 기본 권한 데이터 삽입
                String insertRoles = "INSERT INTO role(role_id, name) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN')";
                ps = conn.prepareStatement(insertRoles);
                ps.executeUpdate();
            }

        } finally {
            DBUtil.close(conn, ps);
        }
    }

    /**
     * 초기 관리자 계정 생성
     */
    private static void insertInitialAdminUser() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            // 관리자 계정이 이미 존재하는지 확인
            String checkAdminSql = "SELECT user_id FROM user WHERE name = 'admin'";
            ps = conn.prepareStatement(checkAdminSql);
            rs = ps.executeQuery();

            if (!rs.next()) {
                // 관리자 계정이 없으면 생성
                ps.close();
                rs.close();

                // 관리자 계정 생성
                String insertAdminSql = "INSERT INTO user (name, password, email) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(insertAdminSql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, "admin");
                ps.setString(2, "admin1234"); // 실제 운영에서는 암호화된 비밀번호를 사용해야 함
                ps.setString(3, "admin@example.com");
                ps.executeUpdate();

                // 생성된 user_id 가져오기
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int adminUserId = rs.getInt(1);

                    ps.close();
                    rs.close();

                    // 관리자 권한 부여 (ROLE_ADMIN = 2)
                    String insertUserRoleSql = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
                    ps = conn.prepareStatement(insertUserRoleSql);
                    ps.setInt(1, adminUserId);
                    ps.setInt(2, 2); // ROLE_ADMIN
                    ps.executeUpdate();

                    System.out.println("👤 초기 관리자 계정 생성 완료 (admin/admin1234)");
                }
            } else {
                System.out.println("👤 관리자 계정이 이미 존재합니다.");
            }

        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
}