package org.example.lionboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 게시글(Post) 엔티티
 */
@Entity
@Table(name = "posts")
@Getter @Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 게시글 제목
     */
    @Column(nullable = false, length = 255)
    private String title;

    /**
     * 게시글 내용
     * - @Lob: 대용량 텍스트 지원
     */
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * 게시글 작성일
     * - @Column(updatable = false): 수정 불가
     */
    @CreationTimestamp
    @Column(name = "posted_date", updatable = false)
    private LocalDateTime postedDate;

    /**
     * 작성자 (User와 ManyToOne 관계)
     * - FetchType.LAZY: 게시글 조회 시 작성자는 필요할 때만 조회
     * - @JoinColumn: users 테이블의 id와 조인
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 게시글 생성을 위한 생성자
     */
    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    /**
     * 작성자 ID 반환 (user.getId() 호출)
     * Service/Controller에서 편리하게 사용
     */
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }
}