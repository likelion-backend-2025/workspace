package net.likelion.lionboard.board.view;

import net.likelion.lionboard.board.dto.BoardDTO;
import net.likelion.lionboard.board.service.BoardService;
import net.likelion.lionboard.user.dto.UserDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 게시판 관련 모든 콘솔 출력을 담당하는 View 클래스
 */
public class BoardView {

    // === 메뉴 출력 ===

    /**
     * 비회원 메인 메뉴 출력
     */
    public void showGuestMenu() {
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
     * 로그인 후 메뉴 출력
     */
    public void showUserMenu(String userName, boolean isAdmin) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("     게시판 메뉴 (" + userName + "님)");
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

        if (isAdmin) {
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
     * 사용자 관리 메뉴 출력
     */
    public void showUserManagementMenu() {
        System.out.println("\n👥 사용자 관리");
        System.out.println("1. 전체 사용자 목록");
        System.out.println("2. 관리자 권한 부여");
        System.out.println("3. 관리자 권한 제거");
        System.out.print("선택: ");
    }

    // === 입력 프롬프트 출력 ===

    /**
     * 웰컴 메시지 출력
     */
    public void showWelcomeMessage() {
        System.out.println("🌟 콘솔 게시판 시스템에 오신 것을 환영합니다! 🌟");
    }

    /**
     * 종료 메시지 출력
     */
    public void showExitMessage() {
        System.out.println("👋 프로그램을 종료합니다. 안녕히 가세요!");
    }

    /**
     * 로그인 화면 출력
     */
    public void showLoginScreen() {
        System.out.println("\n🔐 로그인");
    }

    /**
     * 회원가입 화면 출력
     */
    public void showRegisterScreen() {
        System.out.println("\n📝 회원가입");
    }

    /**
     * 게시글 작성 화면 출력
     */
    public void showWriteBoardScreen() {
        System.out.println("\n✏️ 게시글 작성");
    }

    /**
     * 페이지 번호 입력 프롬프트
     */
    public void showPageNumberPrompt() {
        System.out.print("페이지 번호 (기본 1): ");
    }

    /**
     * 사용자명 입력 프롬프트
     */
    public void showUsernamePrompt() {
        System.out.print("사용자명: ");
    }

    /**
     * 비밀번호 입력 프롬프트
     */
    public void showPasswordPrompt() {
        System.out.print("비밀번호: ");
    }

    /**
     * 이메일 입력 프롬프트
     */
    public void showEmailPrompt() {
        System.out.print("이메일: ");
    }

    /**
     * 게시글 번호 입력 프롬프트
     */
    public void showBoardIdPrompt() {
        System.out.print("조회할 게시글 번호: ");
    }

    /**
     * 수정할 게시글 번호 입력 프롬프트
     */
    public void showUpdateBoardIdPrompt() {
        System.out.print("수정할 게시글 번호: ");
    }

    /**
     * 삭제할 게시글 번호 입력 프롬프트
     */
    public void showDeleteBoardIdPrompt() {
        System.out.print("삭제할 게시글 번호: ");
    }

    /**
     * 제목 입력 프롬프트
     */
    public void showTitlePrompt() {
        System.out.print("제목: ");
    }

    /**
     * 새 제목 입력 프롬프트
     */
    public void showNewTitlePrompt() {
        System.out.print("새 제목: ");
    }

    /**
     * 검색 키워드 입력 프롬프트
     */
    public void showSearchKeywordPrompt() {
        System.out.print("검색할 키워드: ");
    }

    /**
     * 사용자 ID 입력 프롬프트 (관리자 권한 부여)
     */
    public void showGrantAdminPrompt() {
        System.out.print("관리자 권한을 부여할 사용자 ID: ");
    }

    /**
     * 사용자 ID 입력 프롬프트 (관리자 권한 제거)
     */
    public void showRevokeAdminPrompt() {
        System.out.print("관리자 권한을 제거할 사용자 ID: ");
    }

    /**
     * 관리자 게시글 삭제 프롬프트
     */
    public void showAdminDeleteBoardPrompt() {
        System.out.print("삭제할 게시글 번호: ");
    }

    /**
     * 다중 라인 입력 안내 메시지
     */
    public void showMultiLineInputMessage(String fieldName) {
        System.out.println(fieldName + " (여러 줄 입력 가능, 마지막에 '.'만 입력하면 종료):");
    }

    /**
     * 확인 메시지 출력
     */
    public void showConfirmMessage(String message) {
        System.out.print(message + " (y/N): ");
    }

    // === 게시글 관련 출력 ===

    /**
     * 게시글 목록 출력
     */
    public void displayBoardList(BoardService.BoardListResult result) {
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
     * 게시글 상세 출력
     */
    public void displayBoard(BoardService.BoardViewResult result) {
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
     * 내 게시글 목록 출력
     */
    public void displayMyBoards(List<BoardDTO> boards) {
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
     * 검색 결과 출력
     */
    public void displaySearchResults(String keyword, List<BoardService.BoardListItem> results) {
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

    // === 사용자 정보 출력 ===

    /**
     * 사용자 정보 출력
     */
    public void displayUserInfo(UserDTO user, List<String> roles, List<String> rawRoles) {
        System.out.println("\n👤 내 정보");
        System.out.println("-".repeat(30));
        System.out.println("사용자 ID: " + user.getUserId());
        System.out.println("사용자명: " + user.getName());
        System.out.println("이메일: " + user.getEmail());
        if (user.getRegdate() != null) {
            System.out.println("가입일: " + user.getRegdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        // 권한 정보 표시
        System.out.print("권한: ");
        if (roles.isEmpty()) {
            System.out.println("없음");
        } else {
            String rolesDisplay = String.join(", ", roles);
            System.out.println(rolesDisplay);

            // 관리자 권한이 있으면 특별 표시
            if (rawRoles.contains("ROLE_ADMIN")) {
                System.out.println("🔑 관리자 권한이 있습니다.");
            }
        }

        System.out.println("-".repeat(30));
    }

    /**
     * 로그아웃 메시지 출력
     */
    public void showLogoutMessage(String userName) {
        System.out.println("👋 " + userName + "님, 로그아웃 되었습니다.");
    }

    // === 관리자 기능 출력 ===

    /**
     * 전체 게시글 관리 화면 출력
     */
    public void showAllBoardManagementScreen() {
        System.out.println("\n📋 전체 게시글 관리");
    }

    /**
     * 게시글 관리 메뉴 출력
     */
    public void showBoardManagementMenu() {
        System.out.println("\n1. 게시글 강제 삭제");
        System.out.print("선택 (0: 돌아가기): ");
    }

    /**
     * 전체 사용자 목록 헤더 출력
     */
    public void showAllUsersHeader() {
        System.out.println("\n📋 전체 사용자 목록");
    }

    /**
     * 시스템 통계 헤더 출력
     */
    public void showSystemStatsHeader() {
        System.out.println("\n📊 시스템 통계");
    }

    /**
     * 게시글 통계 출력
     */
    public void displayBoardStats(BoardService.BoardStats stats) {
        System.out.println("📊 게시글 통계");
        System.out.println("전체 게시글 수: " + stats.getTotalBoards() + "개");
        System.out.println("오늘 작성된 게시글: " + stats.getTodayBoards() + "개");
    }

    // === 메시지 출력 ===

    /**
     * 성공 메시지 출력
     */
    public void showSuccessMessage(String message) {
        System.out.println("✅ " + message);
    }

    /**
     * 에러 메시지 출력
     */
    public void showErrorMessage(String message) {
        System.err.println("❌ " + message);
    }

    /**
     * 정보 메시지 출력
     */
    public void showInfoMessage(String message) {
        System.out.println("ℹ️ " + message);
    }

    /**
     * 진행 메시지 출력 (계속하려면 Enter...)
     */
    public void showContinueMessage() {
        System.out.print("\n계속하려면 Enter를 누르세요...");
    }

    /**
     * 게스트 안내 메시지
     */
    public void showGuestMessage() {
        System.out.println("\n게시글을 자세히 보려면 로그인해주세요.");
    }
}