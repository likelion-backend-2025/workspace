package net.likelion.lionboard.board.dto;


import java.time.LocalDateTime;

/**
 * Board 테이블의 데이터를 담는 DTO 클래스
 */
public class BoardDTO {
    private int boardId;
    private String title;
    private String content;
    private int userId;
    private LocalDateTime regdate;
    private int viewCnt;

    // 기본 생성자
    public BoardDTO() {
    }

    // 매개변수 생성자 (등록용 - boardId, regdate, viewCnt 제외)
    public BoardDTO(String title, String content, int userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    // 전체 매개변수 생성자
    public BoardDTO(int boardId, String title, String content, int userId, LocalDateTime regdate, int viewCnt) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.regdate = regdate;
        this.viewCnt = viewCnt;
    }

    // Getter와 Setter
    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getRegdate() {
        return regdate;
    }

    public void setRegdate(LocalDateTime regdate) {
        this.regdate = regdate;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardId=" + boardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", regdate=" + regdate +
                ", viewCnt=" + viewCnt +
                '}';
    }


}