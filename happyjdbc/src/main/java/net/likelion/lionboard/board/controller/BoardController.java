package net.likelion.lionboard.board.controller;

import net.likelion.lionboard.board.dto.BoardDTO;
import net.likelion.lionboard.board.service.BoardService;
import net.likelion.lionboard.board.view.BoardView;
import net.likelion.lionboard.user.dto.UserDTO;
import net.likelion.lionboard.user.service.UserService;

import java.util.List;
import java.util.Scanner;

/**
 * 콘솔 기반 게시판 시스템의 메인 컨트롤러
 * 사용자 입력 처리와 비즈니스 로직 호출만 담당
 */
class BoardController {
    // 페이지당 표시할 게시글 수
    private static final int PAGE_SIZE = 5;

    private Scanner scanner;
    private UserService userService;
    private BoardService boardService;
    private BoardView boardView;
    private UserDTO currentUser;

    public BoardController() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService();
        this.boardService = new BoardService();
        this.boardView = new BoardView();
        this.currentUser = null;
    }

    /**
     * 애플리케이션 시작점
     */
    public void start() {
        boardView.showWelcomeMessage();

        while (true) {
            if (currentUser == null) {
                // 로그인 전 메뉴
                boardView.showGuestMenu();
                int choice = getIntInput();

                switch (choice) {
                    case 1: handleLogin(); break;
                    case 2: handleRegister(); break;
                    case 3: handleGuestBoardList(); break;
                    case 0:
                        boardView.showExitMessage();
                        return;
                    default:
                        boardView.showErrorMessage("잘못된 선택입니다. 다시 입력해주세요.");
                }
            } else {
                // 로그인 후 메뉴
                boardView.showUserMenu(currentUser.getName(), isAdmin());
                int choice = getIntInput();

                switch (choice) {
                    case 1: handleBoardList(); break;
                    case 2: handleViewBoard(); break;
                    case 3: handleWriteBoard(); break;
                    case 4: handleMyBoards(); break;
                    case 5: handleSearchBoard(); break;
                    case 6: handleUpdateBoard(); break;
                    case 7: handleDeleteBoard(); break;
                    case 8: handleUserInfo(); break;
                    case 9:
                        if (isAdmin()) handleUserManagement();
                        else boardView.showErrorMessage("관리자 권한이 필요합니다.");
                        break;
                    case 10:
                        if (isAdmin()) handleAllBoardManagement();
                        else boardView.showErrorMessage("관리자 권한이 필요합니다.");
                        break;
                    case 11:
                        if (isAdmin()) handleSystemStats();
                        else boardView.showErrorMessage("관리자 권한이 필요합니다.");
                        break;
                    case 0: handleLogout(); break;
                    default:
                        boardView.showErrorMessage("잘못된 선택입니다. 다시 입력해주세요.");
                }
            }
        }
    }

    /**
     * 로그인 처리
     */
    private void handleLogin() {
        boardView.showLoginScreen();

        boardView.showUsernamePrompt();
        String username = scanner.nextLine().trim();

        boardView.showPasswordPrompt();
        String password = scanner.nextLine().trim();

        UserDTO user = userService.loginUser(username, password);
        if (user != null) {
            this.currentUser = user;
        }
    }

    /**
     * 회원가입 처리
     */
    private void handleRegister() {
        boardView.showRegisterScreen();

        boardView.showUsernamePrompt();
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            boardView.showErrorMessage("사용자명을 입력해주세요.");
            return;
        }

        boardView.showPasswordPrompt();
        String password = scanner.nextLine().trim();
        if (password.isEmpty()) {
            boardView.showErrorMessage("비밀번호를 입력해주세요.");
            return;
        }

        boardView.showEmailPrompt();
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) {
            boardView.showErrorMessage("이메일을 입력해주세요.");
            return;
        }

        userService.registerUser(username, password, email);
    }

    /**
     * 게시글 목록 보기 (비회원)
     */
    private void handleGuestBoardList() {
        boardView.showPageNumberPrompt();
        String pageInput = scanner.nextLine().trim();
        int page = pageInput.isEmpty() ? 1 : parseInt(pageInput, 1);

        try {
            BoardService.BoardListResult result = boardService.listBoards(page, PAGE_SIZE);
            boardView.displayBoardList(result);
        } catch (Exception e) {
            boardView.showErrorMessage(e.getMessage());
        }

        boardView.showGuestMessage();
        boardView.showContinueMessage();
        scanner.nextLine();
    }

    /**
     * 게시글 목록 보기
     */
    private void handleBoardList() {
        boardView.showPageNumberPrompt();
        String pageInput = scanner.nextLine().trim();
        int page = pageInput.isEmpty() ? 1 : parseInt(pageInput, 1);

        try {
            BoardService.BoardListResult result = boardService.listBoards(page, PAGE_SIZE);
            boardView.displayBoardList(result);
        } catch (Exception e) {
            boardView.showErrorMessage(e.getMessage());
        }
    }

    /**
     * 게시글 보기
     */
    private void handleViewBoard() {
        boardView.showBoardIdPrompt();
        int boardId = getIntInput();

        if (boardId <= 0) {
            boardView.showErrorMessage("올바른 게시글 번호를 입력해주세요.");
            return;
        }

        try {
            BoardService.BoardViewResult result = boardService.viewBoard(boardId);
            if (result != null) {
                boardView.displayBoard(result);
            } else {
                boardView.showErrorMessage("해당 게시글을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            boardView.showErrorMessage(e.getMessage());
        }

        boardView.showContinueMessage();
        scanner.nextLine();
    }

    /**
     * 게시글 작성
     */
    private void handleWriteBoard() {
        boardView.showWriteBoardScreen();

        boardView.showTitlePrompt();
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            boardView.showErrorMessage("제목을 입력해주세요.");
            return;
        }

        String content = getMultiLineInput("내용");
        if (content.trim().isEmpty()) {
            boardView.showErrorMessage("내용을 입력해주세요.");
            return;
        }

        try {
            int boardId = boardService.writeBoard(title, content, currentUser.getUserId());
            boardView.showSuccessMessage("게시글이 작성되었습니다! 게시글 ID: " + boardId);
        } catch (Exception e) {
            boardView.showErrorMessage(e.getMessage());
        }
    }

    /**
     * 내 게시글 보기
     */
    private void handleMyBoards() {
        try {
            List<BoardDTO> boards = boardService.listMyBoards(currentUser.getUserId());
            boardView.displayMyBoards(boards);
        } catch (Exception e) {
            boardView.showErrorMessage(e.getMessage());
        }
    }

    /**
     * 게시글 검색
     */
    private void handleSearchBoard() {
        boardView.showSearchKeywordPrompt();
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            boardView.showErrorMessage("검색 키워드를 입력해주세요.");
            return;
        }

        try {
            List<BoardService.BoardListItem> results = boardService.searchBoards(keyword);
            boardView.displaySearchResults(keyword, results);
        } catch (Exception e) {
            boardView.showErrorMessage(e.getMessage());
        }
    }

    /**
     * 게시글 수정
     */
    private void handleUpdateBoard() {
        boardView.showUpdateBoardIdPrompt();
        int boardId = getIntInput();

        if (boardId <= 0) {
            boardView.showErrorMessage("올바른 게시글 번호를 입력해주세요.");
            return;
        }

        boardView.showNewTitlePrompt();
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            boardView.showErrorMessage("제목을 입력해주세요.");
            return;
        }

        String content = getMultiLineInput("새 내용");
        if (content.trim().isEmpty()) {
            boardView.showErrorMessage("내용을 입력해주세요.");
            return;
        }

        try {
            boolean success = boardService.updateBoard(boardId, title, content, currentUser.getUserId());
            if (success) {
                boardView.showSuccessMessage("게시글이 수정되었습니다!");
            } else {
                boardView.showErrorMessage("게시글 수정에 실패했습니다.");
            }
        } catch (IllegalArgumentException | SecurityException e) {
            boardView.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            boardView.showErrorMessage("게시글 수정 중 오류가 발생했습니다.");
        }
    }

    /**
     * 게시글 삭제
     */
    private void handleDeleteBoard() {
        boardView.showDeleteBoardIdPrompt();
        int boardId = getIntInput();

        if (boardId <= 0) {
            boardView.showErrorMessage("올바른 게시글 번호를 입력해주세요.");
            return;
        }

        if (!confirmAction("정말로 삭제하시겠습니까?")) {
            boardView.showInfoMessage("삭제가 취소되었습니다.");
            return;
        }

        try {
            boolean success = boardService.deleteBoard(boardId, currentUser.getUserId());
            if (success) {
                boardView.showSuccessMessage("게시글이 삭제되었습니다!");
            } else {
                boardView.showErrorMessage("게시글 삭제에 실패했습니다.");
            }
        } catch (IllegalArgumentException | SecurityException e) {
            boardView.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            boardView.showErrorMessage("게시글 삭제 중 오류가 발생했습니다.");
        }
    }

    /**
     * 사용자 정보 보기
     */
    private void handleUserInfo() {
        try {
            // 사용자 권한 정보 조회 (사용자 친화적 표시용)
            List<String> userRoles = userService.getUserRoles(currentUser.getUserId());
            // 원본 권한 정보 조회 (관리자 권한 체크용)
            List<String> rawRoles = userService.getUserRolesRaw(currentUser.getUserId());

            boardView.displayUserInfo(currentUser, userRoles, rawRoles);
        } catch (Exception e) {
            boardView.showErrorMessage("사용자 정보 조회 중 오류: " + e.getMessage());
        }
    }

    /**
     * 로그아웃
     */
    private void handleLogout() {
        boardView.showLogoutMessage(currentUser.getName());
        this.currentUser = null;
    }

    /**
     * 관리자 - 사용자 관리
     */
    private void handleUserManagement() {
        boardView.showUserManagementMenu();
        int choice = getIntInput();

        switch (choice) {
            case 1: showAllUsers(); break;
            case 2: grantAdminRole(); break;
            case 3: revokeAdminRole(); break;
            default: boardView.showErrorMessage("잘못된 선택입니다.");
        }
    }

    /**
     * 관리자 - 모든 게시글 관리
     */
    private void handleAllBoardManagement() {
        boardView.showAllBoardManagementScreen();

        try {
            BoardService.BoardListResult result = boardService.listBoards(1, PAGE_SIZE);
            boardView.displayBoardList(result);
        } catch (Exception e) {
            boardView.showErrorMessage(e.getMessage());
        }

        boardView.showBoardManagementMenu();
        int choice = getIntInput();

        if (choice == 1) {
            adminDeleteBoard();
        }
    }

    /**
     * 관리자 - 게시글 강제 삭제
     */
    private void adminDeleteBoard() {
        boardView.showAdminDeleteBoardPrompt();
        int boardId = getIntInput();

        if (boardId <= 0) {
            boardView.showErrorMessage("올바른 게시글 번호를 입력해주세요.");
            return;
        }

        if (!confirmAction("정말로 삭제하시겠습니까?")) {
            return;
        }

        try {
            boolean success = boardService.adminDeleteBoard(boardId);
            if (success) {
                boardView.showSuccessMessage("게시글이 삭제되었습니다.");
            } else {
                boardView.showErrorMessage("게시글 삭제에 실패했습니다.");
            }
        } catch (IllegalArgumentException e) {
            boardView.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            boardView.showErrorMessage("게시글 삭제 중 오류가 발생했습니다.");
        }
    }

    /**
     * 관리자 - 시스템 통계
     */
    private void handleSystemStats() {
        boardView.showSystemStatsHeader();

        try {
            userService.showSystemStats();

            BoardService.BoardStats stats = boardService.getBoardStats();
            boardView.displayBoardStats(stats);
        } catch (Exception e) {
            boardView.showErrorMessage("통계 조회 중 오류: " + e.getMessage());
        }
    }

    // === 유틸리티 메서드들 ===

    /**
     * 다중 라인 입력 받기
     */
    private String getMultiLineInput(String fieldName) {
        boardView.showMultiLineInputMessage(fieldName);
        StringBuilder content = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals(".")) {
            if (content.length() > 0) {
                content.append("\n");
            }
            content.append(line);
        }

        return content.toString();
    }

    /**
     * 확인 대화상자
     */
    private boolean confirmAction(String message) {
        boardView.showConfirmMessage(message);
        String confirm = scanner.nextLine().trim().toLowerCase();
        return confirm.equals("y") || confirm.equals("yes");
    }

    /**
     * 정수 입력을 안전하게 받는 메서드
     */
    private int getIntInput() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * 문자열을 정수로 변환 (기본값 제공)
     */
    private int parseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 현재 사용자가 관리자인지 확인
     */
    private boolean isAdmin() {
        try {
            return userService.hasAdminRole(currentUser.getUserId());
        } catch (Exception e) {
            return false;
        }
    }

    // === 사용자 관리 관련 메서드들 ===

    private void showAllUsers() {
        try {
            boardView.showAllUsersHeader();
            userService.listAllUsers();
        } catch (Exception e) {
            boardView.showErrorMessage("사용자 목록 조회 중 오류: " + e.getMessage());
        }
    }

    private void grantAdminRole() {
        boardView.showGrantAdminPrompt();
        int userId = getIntInput();

        if (userId <= 0) {
            boardView.showErrorMessage("올바른 사용자 ID를 입력해주세요.");
            return;
        }

        userService.grantAdminRole(userId);
    }

    private void revokeAdminRole() {
        boardView.showRevokeAdminPrompt();
        int userId = getIntInput();

        if (userId <= 0) {
            boardView.showErrorMessage("올바른 사용자 ID를 입력해주세요.");
            return;
        }

        if (userId == currentUser.getUserId()) {
            boardView.showErrorMessage("자신의 관리자 권한은 제거할 수 없습니다.");
            return;
        }

        userService.revokeAdminRole(userId);
    }
}