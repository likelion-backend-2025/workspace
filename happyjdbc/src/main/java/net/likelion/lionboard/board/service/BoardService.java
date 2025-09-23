package net.likelion.lionboard.board.service;

import net.likelion.lionboard.board.dao.BoardDAO;
import net.likelion.lionboard.board.dto.BoardDTO;
import net.likelion.lionboard.user.dao.UserDAO;
import net.likelion.lionboard.user.dto.UserDTO;

import java.util.List;

/**
 * 게시판 관련 비즈니스 로직을 처리하는 서비스
 * 순수하게 비즈니스 로직만 담당하며, 출력은 Controller에서 처리합니다.
 */
public class BoardService {
    private BoardDAO boardDAO;
    private UserDAO userDAO;

    public BoardService() {
        this.boardDAO = new BoardDAO();
        this.userDAO = new UserDAO();
    }

    /**
     * 새 게시글을 작성합니다.
     * @return 생성된 게시글 ID, 실패시 -1
     */
    public int writeBoard(String title, String content, int userId) {
        try {
            // 입력값 검증
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("제목을 입력해주세요.");
            }
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException("내용을 입력해주세요.");
            }

            BoardDTO board = new BoardDTO(title.trim(), content.trim(), userId);
            return boardDAO.insertBoard(board);

        } catch (Exception e) {
            throw new RuntimeException("게시글 작성 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 게시글을 조회합니다. (조회 시 조회수 증가)
     * @return 게시글 정보와 작성자 정보가 포함된 객체, 없으면 null
     */
    public BoardViewResult viewBoard(int boardId) {
        try {
            // 1. 게시글 조회
            BoardDTO board = boardDAO.selectBoardById(boardId);
            if (board == null) {
                return null;
            }

            // 2. 조회수 증가 (비즈니스 로직)
            boardDAO.increaseViewCount(boardId);
            board.setViewCnt(board.getViewCnt() + 1);

            // 3. 작성자 정보 조회
            UserDTO author = userDAO.selectUserById(board.getUserId());

            return new BoardViewResult(board, author);

        } catch (Exception e) {
            throw new RuntimeException("게시글 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 수정용 게시글 조회 (조회수 증가 없음)
     */
    public BoardDTO getBoardForEdit(int boardId) {
        try {
            return boardDAO.selectBoardById(boardId);
        } catch (Exception e) {
            throw new RuntimeException("게시글 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 페이징된 게시글 목록을 조회합니다.
     */
    public BoardListResult listBoards(int page, int size) {
        try {
            if (page < 1 || size < 1) {
                throw new IllegalArgumentException("페이지와 크기는 1 이상이어야 합니다.");
            }

            int offset = (page - 1) * size;
            List<BoardDTO> boards = boardDAO.selectBoardsWithPaging(offset, size);
            int totalCount = boardDAO.getBoardCount();
            int totalPages = (int) Math.ceil((double) totalCount / size);

            // 작성자 정보 추가
            List<BoardListItem> boardItems = boards.stream()
                    .map(board -> {
                        UserDTO author = userDAO.selectUserById(board.getUserId());
                        return new BoardListItem(board, author);
                    })
                    .toList();

            return new BoardListResult(boardItems, page, totalPages, totalCount);

        } catch (Exception e) {
            throw new RuntimeException("게시글 목록 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 특정 사용자의 게시글 목록을 조회합니다.
     */
    public List<BoardDTO> listMyBoards(int userId) {
        try {
            return boardDAO.selectBoardsByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("내 게시글 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 게시글을 검색합니다.
     */
    public List<BoardListItem> searchBoards(String keyword) {
        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                throw new IllegalArgumentException("검색어를 입력해주세요.");
            }

            List<BoardDTO> boards = boardDAO.selectBoardsByTitle(keyword.trim());

            // 작성자 정보 추가
            return boards.stream()
                    .map(board -> {
                        UserDTO author = userDAO.selectUserById(board.getUserId());
                        return new BoardListItem(board, author);
                    })
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("게시글 검색 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 게시글을 수정합니다.
     */
    public boolean updateBoard(int boardId, String title, String content, int currentUserId) {
        try {
            // 입력값 검증
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("제목을 입력해주세요.");
            }
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException("내용을 입력해주세요.");
            }

            // 게시글 존재 및 권한 확인
            BoardDTO board = getBoardForEdit(boardId);
            if (board == null) {
                throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
            }
            if (board.getUserId() != currentUserId) {
                throw new SecurityException("자신이 작성한 게시글만 수정할 수 있습니다.");
            }

            // 수정 실행
            board.setTitle(title.trim());
            board.setContent(content.trim());
            int result = boardDAO.updateBoard(board);

            return result > 0;

        } catch (IllegalArgumentException | SecurityException e) {
            throw e; // 비즈니스 예외는 그대로 전달
        } catch (Exception e) {
            throw new RuntimeException("게시글 수정 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 게시글을 삭제합니다.
     */
    public boolean deleteBoard(int boardId, int currentUserId) {
        try {
            // 게시글 존재 및 권한 확인
            BoardDTO board = getBoardForEdit(boardId);
            if (board == null) {
                throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
            }
            if (board.getUserId() != currentUserId) {
                throw new SecurityException("자신이 작성한 게시글만 삭제할 수 있습니다.");
            }

            // 삭제 실행
            int result = boardDAO.deleteBoard(boardId);
            return result > 0;

        } catch (IllegalArgumentException | SecurityException e) {
            throw e; // 비즈니스 예외는 그대로 전달
        } catch (Exception e) {
            throw new RuntimeException("게시글 삭제 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 관리자 - 게시글 강제 삭제
     */
    public boolean adminDeleteBoard(int boardId) {
        try {
            BoardDTO board = getBoardForEdit(boardId);
            if (board == null) {
                throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
            }

            int result = boardDAO.deleteBoard(boardId);
            return result > 0;

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("게시글 삭제 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 게시글 통계를 조회합니다.
     */
    public BoardStats getBoardStats() {
        try {
            int totalBoards = boardDAO.getBoardCount();
            // TODO: 오늘 작성된 게시글 수, 인기 게시글 등 추가 통계 구현
            return new BoardStats(totalBoards, 0);

        } catch (Exception e) {
            throw new RuntimeException("게시글 통계 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // DTO 클래스들

    /**
     * 게시글 조회 결과
     */
    public static class BoardViewResult {
        private final BoardDTO board;
        private final UserDTO author;

        public BoardViewResult(BoardDTO board, UserDTO author) {
            this.board = board;
            this.author = author;
        }

        public BoardDTO getBoard() { return board; }
        public UserDTO getAuthor() { return author; }
    }

    /**
     * 게시글 목록 아이템
     */
    public static class BoardListItem {
        private final BoardDTO board;
        private final UserDTO author;

        public BoardListItem(BoardDTO board, UserDTO author) {
            this.board = board;
            this.author = author;
        }

        public BoardDTO getBoard() { return board; }
        public UserDTO getAuthor() { return author; }
    }

    /**
     * 게시글 목록 결과
     */
    public static class BoardListResult {
        private final List<BoardListItem> boards;
        private final int currentPage;
        private final int totalPages;
        private final int totalCount;

        public BoardListResult(List<BoardListItem> boards, int currentPage, int totalPages, int totalCount) {
            this.boards = boards;
            this.currentPage = currentPage;
            this.totalPages = totalPages;
            this.totalCount = totalCount;
        }

        public List<BoardListItem> getBoards() { return boards; }
        public int getCurrentPage() { return currentPage; }
        public int getTotalPages() { return totalPages; }
        public int getTotalCount() { return totalCount; }
    }

    /**
     * 게시글 통계
     */
    public static class BoardStats {
        private final int totalBoards;
        private final int todayBoards;

        public BoardStats(int totalBoards, int todayBoards) {
            this.totalBoards = totalBoards;
            this.todayBoards = todayBoards;
        }

        public int getTotalBoards() { return totalBoards; }
        public int getTodayBoards() { return todayBoards; }
    }
}