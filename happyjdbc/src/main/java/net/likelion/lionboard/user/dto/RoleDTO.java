package net.likelion.lionboard.user.dto;

import java.util.Objects;

/**
 * Role 테이블의 데이터를 담는 DTO 클래스
 */
public class RoleDTO {
    private int roleId;
    private String name;

    // 기본 생성자
    public RoleDTO() {
    }

    // 매개변수 생성자
    public RoleDTO(int roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    // Getter와 Setter
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return roleId == roleDTO.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}
