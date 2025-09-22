package net.likelion.lionboard.board.dao;


import net.likelion.lionboard.board.dto.BoardDTO;
import net.likelion.lionboard.common.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Board 테이블에 대한 데이터 액세스 객체
 * 순수하게 데이터 접근만 담당하며, 비즈니스 로직은 포함하지 않습니다.
 */
public class BoardDAO {

    /**
     * 새 게시글을 등록합니다.
     */
    public int insertBoard(BoardDTO board) {
        String sql = "INSERT INTO board (title, content, user_id) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setInt(3, board.getUserId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("게시글 등록에 실패했습니다.");
            }

            // 생성된 ID 반환
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("생성된 ID를 가져올 수 없습니다.");
            }

        } catch (Exception e) {
            throw new RuntimeException("게시글 등록 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * ID로 게시글을 조회합니다. (조회수 증가 없음)
     * 조회수 증가는 Service 계층에서 별도로 처리합니다.
     */
    public BoardDTO selectBoardById(int boardId) {
        String sql = "SELECT board_id, title, content, user_id, regdate, view_cnt FROM board WHERE board_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, boardId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToBoard(rs);
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException("게시글 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * 모든 게시글을 조회합니다 (최신순).
     */
    public List<BoardDTO> selectAllBoards() {
        String sql = "SELECT board_id, title, content, user_id, regdate, view_cnt FROM board ORDER BY regdate DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BoardDTO> boards = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                boards.add(mapResultSetToBoard(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException("게시글 목록 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return boards;
    }

    /**
     * 페이징을 적용한 게시글 목록을 조회합니다.
     */
    public List<BoardDTO> selectBoardsWithPaging(int offset, int limit) {
        String sql = """
                SELECT board_id, title, content, user_id, regdate, view_cnt 
                FROM board 
                ORDER BY regdate DESC 
                LIMIT ? OFFSET ?
                """;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BoardDTO> boards = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            rs = ps.executeQuery();

            while (rs.next()) {
                boards.add(mapResultSetToBoard(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException("게시글 페이징 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return boards;
    }

    /**
     * 특정 사용자의 게시글을 조회합니다.
     */
    public List<BoardDTO> selectBoardsByUserId(int userId) {
        String sql = """
                SELECT board_id, title, content, user_id, regdate, view_cnt 
                FROM board 
                WHERE user_id = ? 
                ORDER BY regdate DESC
                """;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BoardDTO> boards = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                boards.add(mapResultSetToBoard(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException("사용자별 게시글 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return boards;
    }

    /**
     * 제목으로 게시글을 검색합니다.
     */
    public List<BoardDTO> selectBoardsByTitle(String keyword) {
        String sql = """
                SELECT board_id, title, content, user_id, regdate, view_cnt 
                FROM board 
                WHERE title LIKE ? 
                ORDER BY regdate DESC
                """;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BoardDTO> boards = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                boards.add(mapResultSetToBoard(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException("게시글 제목 검색 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return boards;
    }

    /**
     * 게시글을 수정합니다.
     */
    public int updateBoard(BoardDTO board) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE board_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setInt(3, board.getBoardId());

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("게시글 수정 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    /**
     * 게시글을 삭제합니다.
     */
    public int deleteBoard(int boardId) {
        String sql = "DELETE FROM board WHERE board_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, boardId);

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("게시글 삭제 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    /**
     * 조회수를 증가시킵니다.
     * 이 메서드는 Service 계층에서 호출되어야 합니다.
     */
    public int increaseViewCount(int boardId) {
        String sql = "UPDATE board SET view_cnt = view_cnt + 1 WHERE board_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, boardId);

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("조회수 증가 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps);
        }
    }

    /**
     * 전체 게시글 수를 조회합니다.
     */
    public int getBoardCount() {
        String sql = "SELECT COUNT(*) FROM board";
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
            throw new RuntimeException("게시글 수 조회 중 오류 발생", e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    /**
     * ResultSet을 BoardDTO로 매핑하는 헬퍼 메서드
     */
    private BoardDTO mapResultSetToBoard(ResultSet rs) throws SQLException {
        BoardDTO board = new BoardDTO();

        board.setBoardId(rs.getInt("board_id"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setUserId(rs.getInt("user_id"));
        board.setViewCnt(rs.getInt("view_cnt"));

        // regdate를 LocalDateTime으로 변환
        Timestamp timestamp = rs.getTimestamp("regdate");
        if (timestamp != null) {
            board.setRegdate(timestamp.toLocalDateTime());
        }

        return board;
    }
}