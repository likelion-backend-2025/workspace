package net.likelion.lionboard.user.dto;


import java.time.LocalDateTime;

/**
 * User 테이블의 데이터를 담는 DTO 클래스
 */
public class UserDTO {
    private int userId;
    private String name;
    private String password;
    private String email;
    private LocalDateTime regdate;

    // 기본 생성자
    public UserDTO() {}

    // 회원가입용 생성자
    public UserDTO(String username, String password, String email) {
        this.name = username;
        this.password = password;
        this.email = email;
    }

    // 전체 생성자
    public UserDTO(int userId, String username, String password, String email, LocalDateTime regdate) {
        this.userId = userId;
        this.name = username;
        this.password = password;
        this.email = email;
        this.regdate = regdate;
    }

    // Getter와 Setter
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getRegdate() { return regdate; }
    public void setRegdate(LocalDateTime regdate) { this.regdate = regdate; }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", regdate=" + regdate +
                '}';
    }
}
