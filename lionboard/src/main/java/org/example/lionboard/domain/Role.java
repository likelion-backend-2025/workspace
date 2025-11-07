package org.example.lionboard.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 사용자 역할(Role) 엔티티
 * - USER: 일반 사용자 권한
 * - ADMIN: 관리자 권한
 */
@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 역할 이름 (예: "USER", "ADMIN")
     * - unique = true: 중복된 역할 이름 방지
     */
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    /**
     * 역할 이름만으로 생성할 수 있는 편의 생성자
     */
    public Role(String name) {
        this.name = name;
    }
}