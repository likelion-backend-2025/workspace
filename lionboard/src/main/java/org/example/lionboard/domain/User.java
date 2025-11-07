package org.example.lionboard.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 사용자(User) 엔티티
 */
@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사용자 이름 (실명)
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * 로그인 ID (고유값)
     * - unique = true: 중복 불가
     */
    @Column(name = "login_id", nullable = false, unique = true, length = 100)
    private String loginId;

    /**
     * 암호화된 비밀번호
     * - BCryptPasswordEncoder로 암호화됨
     */
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * 이메일 주소
     */
    @Column(nullable = false, length = 100)
    private String email;

    /**
     * 가입 일시
     * - @Column(updatable = false): 수정 불가
     */
    @CreationTimestamp
    @Column(name = "joined_date", updatable = false)
    private LocalDateTime joinedDate;

    /**
     * 사용자의 역할 목록 (ManyToMany 관계)
     * - FetchType.LAZY: 필요할 때만 조회 (성능 최적화)
     * - CascadeType.PERSIST: 사용자 저장 시 역할도 함께 저장
     * - user_roles 조인 테이블 자동 생성
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    /**
     * 사용자 생성을 위한 생성자
     */
    public User(String name, String loginId, String password, String email) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
    }

    /**
     * 역할 추가 헬퍼 메소드
     */
    public void addRole(Role role) {
        this.roles.add(role);
    }
}