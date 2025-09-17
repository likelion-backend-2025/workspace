package net.likelion.lionboard.user.dto;


import java.util.Objects;

/**
 * UserRole 테이블의 데이터를 담는 DTO 클래스
 */
public class UserRoleDTO {
    private int userId;
    private int roleId;

    // 기본 생성자
    public UserRoleDTO() {
    }

    // 매개변수 생성자
    public UserRoleDTO(int userId, int roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    // Getter와 Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleDTO that = (UserRoleDTO) o;
        return userId == that.userId && roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}