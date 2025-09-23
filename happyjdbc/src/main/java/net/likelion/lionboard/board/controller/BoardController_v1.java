package net.likelion.lionboard.board.controller;

import net.likelion.lionboard.board.dto.BoardDTO;
import net.likelion.lionboard.board.service.BoardService;
import net.likelion.lionboard.user.dto.UserDTO;
import net.likelion.lionboard.user.service.UserService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * 콘솔 기반 게시판 시스템의 메인 컨트롤러
 */
class BoardController_v1 {
    private Scanner scanner;
    private UserService userService;
    private BoardService boardService;
    private UserDTO currentUser;

    public BoardController_v1() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService();
        this.boardService = new BoardService();
        this.currentUser = null;
    }

    /**
     * 애플리케이션 시작점
     */
    public void start() {
        System.out.println("🌟 콘솔 게시판 시스템에 오신 것을 환영합니다! 🌟");

        while (true) {
            if (currentUser == null) {
                // 로그인 전 메뉴
                showGuestMenu();
                int choice = getIntInput();

                switch (choice) {
                    case 1: handleLogin(); break;
                    case 2: handleRegister(); break;
                    case 3: handleGuestBoardList(); break;
                    case 0:
                        System.out.println("👋 프로그램을 종료합니다. 안녕히 가세요!");
                        return;
                    default:
                        System.out.println("❌ 잘못된 선택입니다. 다시 입력해주세요.");
                }
            } else {
                // 로그인 후 메뉴
                showUserMenu();
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
                        else System.out.println("❌ 관리자 권한이 필요합니다.");
                        break;
                    case 10:
                        if (isAdmin()) handleAllBoardManagement();
                        else System.out.println("❌ 관리자 권한이 필요합니다.");
                        break;
                    case 11:
                        if (isAdmin()) handleSystemStats();
                        else System.out.println("❌ 관리자 권한이 필요합니다.");
                        break;
                    case 0: handleLogout(); break;
                    default:
                        System.out.println("❌ 잘못된 선택입니다. 다시 입력해주세요.");
                }
            }
        }
    }

    /**
     * 로그인 전 메뉴 표시
     */
    private void showGuestMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("          메인 메뉴");
        System.out.println("=".repeat(40));
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 게시글 목록 보기 (비회원)");
        System.out.println("0. 프로그램 종료");
        System.out.println("=".repeat(40));
        System.out.print("선택: ");
    }

    /**
     * 로그인 처리
     */
    private void handleLogin() {
        System.out.println("\n🔐 로그인");
        System.out.print("사용자명: ");
        String username = scanner.nextLine().trim();

        System.out.print("비밀번호: ");
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
        System.out.println("\n📝 회원가입");

        System.out.print("사용자명: ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty()) {
            System.out.println("❌ 사용자명을 입력해주세요.");
            return;
        }

        System.out.print("비밀번호: ");
        String password = scanner.nextLine().trim();

        if (password.isEmpty()) {
            System.out.println("❌ 비밀번호를 입력해주세요.");
            return;
        }

        System.out.print("이메일: ");
        String email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("❌ 이메일을 입력해주세요.");
            return;
        }

        userService.registerUser(username, password, email);
    }

    /**
     * 게시글 목록 보기 (비회원)
     */
    private void handleGuestBoardList() {
        System.out.print("페이지 번호 (기본 1): ");
        String pageInput = scanner.nextLine().trim();
        int page = pageInput.isEmpty() ? 1 : parseInt(pageInput, 1);

        // Service에서 데이터를 받아서 출력
        try {
            BoardService.BoardListResult result = boardService.listBoards(page, 10);
            displayBoardList(result);
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }

        System.out.println("\n게시글을 자세히 보려면 로그인해주세요.");
        System.out.print("계속하려면 Enter를 누르세요...");
        scanner.nextLine();
    }

    /**
     * 게시글 목록 보기
     */
    private void handleBoardList() {
        System.out.print("페이지 번호 (기본 1): ");
        String pageInput = scanner.nextLine().trim();
        int page = pageInput.isEmpty() ? 1 : parseInt(pageInput, 1);

        try {
            BoardService.BoardListResult result = boardService.listBoards(page, 10);
            displayBoardList(result);
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }
    }

    /**
     * 게시글 목록 출력
     */
    private void displayBoardList(BoardService.BoardListResult result) {
        System.out.println("\n📋 게시글 목록 (페이지 " + result.getCurrentPage() + "/" + result.getTotalPages() + ")");
        System.out.println("-".repeat(80));
        System.out.printf("%-5s %-20s %-10s %-15s %-8s\n", "번호", "제목", "작성자", "작성일", "조회수");
        System.out.println("-".repeat(80));

        for (BoardService.BoardListItem item : result.getBoards()) {
            BoardDTO board = item.getBoard();
            UserDTO author = item.getAuthor();

            String title = board.getTitle().length() > 20 ? board.getTitle().substring(0, 17) + "..." : board.getTitle();
            String authorName = author != null ? author.getName() : "알수없음";
            String regdate = board.getRegdate().format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));

            System.out.printf("%-5d %-20s %-10s %-15s %-8d\n",
                    board.getBoardId(), title, authorName, regdate, board.getViewCnt());
        }

        System.out.println("-".repeat(80));
        System.out.println("전체 게시글: " + result.getTotalCount() + "개");
    }

    /**
     * 게시글 보기
     */
    private void handleViewBoard() {
        System.out.print("조회할 게시글 번호: ");
        int boardId = getIntInput();

        if (boardId <= 0) {
            System.out.println("❌ 올바른 게시글 번호를 입력해주세요.");
            return;
        }

        try {
            BoardService.BoardViewResult result = boardService.viewBoard(boardId);
            if (result != null) {
                displayBoard(result);
            } else {
                System.out.println("❌ 해당 게시글을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }

        System.out.print("\n계속하려면 Enter를 누르세요...");
        scanner.nextLine();
    }

    /**
     * 게시글 상세 출력
     */
    private void displayBoard(BoardService.BoardViewResult result) {
        BoardDTO board = result.getBoard();
        UserDTO author = result.getAuthor();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("제목: " + board.getTitle());
        System.out.println("작성자: " + (author != null ? author.getName() : "알 수 없음"));
        System.out.println("작성일: " + board.getRegdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("조회수: " + board.getViewCnt());
        System.out.println("-".repeat(50));
        System.out.println(board.getContent());
        System.out.println("=".repeat(50));
    }

    /**
     * 게시글 작성
     */
    private void handleWriteBoard() {
        System.out.println("\n✏️ 게시글 작성");

        System.out.print("제목: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("❌ 제목을 입력해주세요.");
            return;
        }

        System.out.println("내용 (여러 줄 입력 가능, 마지막에 '.'만 입력하면 종료):");
        StringBuilder content = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals(".")) {
            if (content.length() > 0) {
                content.append("\n");
            }
            content.append(line);
        }

        if (content.toString().trim().isEmpty()) {
            System.out.println("❌ 내용을 입력해주세요.");
            return;
        }

        try {
            int boardId = boardService.writeBoard(title, content.toString(), currentUser.getUserId());
            System.out.println("✅ 게시글이 작성되었습니다! 게시글 ID: " + boardId);
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }
    }

    /**
     * 내 게시글 보기
     */
    private void handleMyBoards() {
        try {
            List<BoardDTO> boards = boardService.listMyBoards(currentUser.getUserId());
            displayMyBoards(boards);
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }
    }

    /**
     * 내 게시글 목록 출력
     */
    private void displayMyBoards(List<BoardDTO> boards) {
        System.out.println("\n📝 내가 작성한 게시글 목록");
        System.out.println("-".repeat(70));
        System.out.printf("%-5s %-30s %-15s %-8s\n", "번호", "제목", "작성일", "조회수");
        System.out.println("-".repeat(70));

        for (BoardDTO board : boards) {
            String title = board.getTitle().length() > 30 ? board.getTitle().substring(0, 27) + "..." : board.getTitle();
            String regdate = board.getRegdate().format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));

            System.out.printf("%-5d %-30s %-15s %-8d\n",
                    board.getBoardId(), title, regdate, board.getViewCnt());
        }

        System.out.println("-".repeat(70));
        System.out.println("총 " + boards.size() + "개의 게시글");
    }

    /**
     * 게시글 검색
     */
    private void handleSearchBoard() {
        System.out.print("검색할 키워드: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("❌ 검색 키워드를 입력해주세요.");
            return;
        }

        try {
            List<BoardService.BoardListItem> results = boardService.searchBoards(keyword);
            displaySearchResults(keyword, results);
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }
    }

    /**
     * 검색 결과 출력
     */
    private void displaySearchResults(String keyword, List<BoardService.BoardListItem> results) {
        System.out.println("\n🔍 '" + keyword + "' 검색 결과");
        System.out.println("-".repeat(70));

        if (results.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            System.out.printf("%-5s %-30s %-10s %-8s\n", "번호", "제목", "작성자", "조회수");
            System.out.println("-".repeat(70));

            for (BoardService.BoardListItem item : results) {
                BoardDTO board = item.getBoard();
                UserDTO author = item.getAuthor();

                String title = board.getTitle().length() > 30 ? board.getTitle().substring(0, 27) + "..." : board.getTitle();
                String authorName = author != null ? author.getName() : "알수없음";

                System.out.printf("%-5d %-30s %-10s %-8d\n",
                        board.getBoardId(), title, authorName, board.getViewCnt());
            }

            System.out.println("-".repeat(70));
            System.out.println("총 " + results.size() + "개의 게시글 발견");
        }
    }

    /**
     * 게시글 수정
     */
    private void handleUpdateBoard() {
        System.out.print("수정할 게시글 번호: ");
        int boardId = getIntInput();

        if (boardId <= 0) {
            System.out.println("❌ 올바른 게시글 번호를 입력해주세요.");
            return;
        }

        System.out.print("새 제목: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("❌ 제목을 입력해주세요.");
            return;
        }

        System.out.println("새 내용 (여러 줄 입력 가능, 마지막에 '.'만 입력하면 종료):");
        StringBuilder content = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals(".")) {
            if (content.length() > 0) {
                content.append("\n");
            }
            content.append(line);
        }

        if (content.toString().trim().isEmpty()) {
            System.out.println("❌ 내용을 입력해주세요.");
            return;
        }

        try {
            boolean success = boardService.updateBoard(boardId, title, content.toString(), currentUser.getUserId());
            if (success) {
                System.out.println("✅ 게시글이 수정되었습니다!");
            } else {
                System.out.println("❌ 게시글 수정에 실패했습니다.");
            }
        } catch (IllegalArgumentException | SecurityException e) {
            System.err.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ 게시글 수정 중 오류가 발생했습니다.");
        }
    }

    /**
     * 게시글 삭제
     */
    private void handleDeleteBoard() {
        System.out.print("삭제할 게시글 번호: ");
        int boardId = getIntInput();

        if (boardId <= 0) {
            System.out.println("❌ 올바른 게시글 번호를 입력해주세요.");
            return;
        }

        System.out.print("정말로 삭제하시겠습니까? (y/N): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            try {
                boolean success = boardService.deleteBoard(boardId, currentUser.getUserId());
                if (success) {
                    System.out.println("✅ 게시글이 삭제되었습니다!");
                } else {
                    System.out.println("❌ 게시글 삭제에 실패했습니다.");
                }
            } catch (IllegalArgumentException | SecurityException e) {
                System.err.println("❌ " + e.getMessage());
            } catch (Exception e) {
                System.err.println("❌ 게시글 삭제 중 오류가 발생했습니다.");
            }
        } else {
            System.out.println("❌ 삭제가 취소되었습니다.");
        }
    }

    /**
     * 사용자 정보 보기
     */
    private void handleUserInfo() {
        System.out.println("\n👤 내 정보");
        System.out.println("-".repeat(30));
        System.out.println("사용자 ID: " + currentUser.getUserId());
        System.out.println("사용자명: " + currentUser.getName());
        System.out.println("이메일: " + currentUser.getEmail());
        if (currentUser.getRegdate() != null) {
            System.out.println("가입일: " + currentUser.getRegdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        System.out.println("-".repeat(30));
    }

    /**
     * 로그아웃
     */
    private void handleLogout() {
        System.out.println("👋 " + currentUser.getName() + "님, 로그아웃 되었습니다.");
        this.currentUser = null;
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
     * 로그인 후 메뉴 표시 (관리자 권한 추가)
     */
    private void showUserMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("     게시판 메뉴 (" + currentUser.getName() + "님)");
        System.out.println("=".repeat(40));
        System.out.println("1. 게시글 목록");
        System.out.println("2. 게시글 보기");
        System.out.println("3. 게시글 작성");
        System.out.println("4. 내 게시글 보기");
        System.out.println("5. 게시글 검색");
        System.out.println("6. 게시글 수정");
        System.out.println("7. 게시글 삭제");
        System.out.println("8. 내 정보 보기");
        System.out.println("0. 로그아웃");
        // 관리자 권한이 있는 경우 관리자 메뉴 표시
        if (isAdmin()) {
            System.out.println("-".repeat(40));
            System.out.println("【관리자 메뉴】");
            System.out.println("9. 사용자 관리");
            System.out.println("10. 모든 게시글 관리");
            System.out.println("11. 시스템 통계");
        }

        System.out.println("=".repeat(40));
        System.out.print("선택: ");
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

    /**
     * 관리자 - 사용자 관리
     */
    private void handleUserManagement() {
        System.out.println("\n👥 사용자 관리");
        System.out.println("1. 전체 사용자 목록");
        System.out.println("2. 관리자 권한 부여");
        System.out.println("3. 관리자 권한 제거");
        System.out.print("선택: ");

        int choice = getIntInput();
        switch (choice) {
            case 1: showAllUsers(); break;
            case 2: grantAdminRole(); break;
            case 3: revokeAdminRole(); break;
            default: System.out.println("❌ 잘못된 선택입니다.");
        }
    }

    /**
     * 전체 사용자 목록 조회
     */
    private void showAllUsers() {
        try {
            System.out.println("\n📋 전체 사용자 목록");
            userService.listAllUsers();
        } catch (Exception e) {
            System.err.println("❌ 사용자 목록 조회 중 오류: " + e.getMessage());
        }
    }

    /**
     * 관리자 권한 부여
     */
    private void grantAdminRole() {
        System.out.print("관리자 권한을 부여할 사용자 ID: ");
        int userId = getIntInput();

        if (userId <= 0) {
            System.out.println("❌ 올바른 사용자 ID를 입력해주세요.");
            return;
        }

        userService.grantAdminRole(userId);
    }

    /**
     * 관리자 권한 제거
     */
    private void revokeAdminRole() {
        System.out.print("관리자 권한을 제거할 사용자 ID: ");
        int userId = getIntInput();

        if (userId <= 0) {
            System.out.println("❌ 올바른 사용자 ID를 입력해주세요.");
            return;
        }

        if (userId == currentUser.getUserId()) {
            System.out.println("❌ 자신의 관리자 권한은 제거할 수 없습니다.");
            return;
        }

        userService.revokeAdminRole(userId);
    }

    /**
     * 관리자 - 모든 게시글 관리
     */
    private void handleAllBoardManagement() {
        System.out.println("\n📋 전체 게시글 관리");

        try {
            BoardService.BoardListResult result = boardService.listBoards(1, 20);
            displayBoardList(result);
        } catch (Exception e) {
            System.err.println("❌ " + e.getMessage());
        }

        System.out.println("\n1. 게시글 강제 삭제");
        System.out.print("선택 (0: 돌아가기): ");

        int choice = getIntInput();
        if (choice == 1) {
            adminDeleteBoard();
        }
    }

    /**
     * 관리자 - 게시글 강제 삭제
     */
    private void adminDeleteBoard() {
        System.out.print("삭제할 게시글 번호: ");
        int boardId = getIntInput();

        if (boardId <= 0) {
            System.out.println("❌ 올바른 게시글 번호를 입력해주세요.");
            return;
        }

        System.out.print("정말로 삭제하시겠습니까? (y/N): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            try {
                boolean success = boardService.adminDeleteBoard(boardId);
                if (success) {
                    System.out.println("✅ 게시글이 삭제되었습니다.");
                } else {
                    System.out.println("❌ 게시글 삭제에 실패했습니다.");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("❌ " + e.getMessage());
            } catch (Exception e) {
                System.err.println("❌ 게시글 삭제 중 오류가 발생했습니다.");
            }
        }
    }

    /**
     * 관리자 - 시스템 통계
     */
    private void handleSystemStats() {
        System.out.println("\n📊 시스템 통계");
        try {
            userService.showSystemStats();

            BoardService.BoardStats stats = boardService.getBoardStats();
            System.out.println("전체 게시글 수: " + stats.getTotalBoards() + "개");
            System.out.println("오늘 작성된 게시글: " + stats.getTodayBoards() + "개");
        } catch (Exception e) {
            System.err.println("❌ 통계 조회 중 오류: " + e.getMessage());
        }
    }
}