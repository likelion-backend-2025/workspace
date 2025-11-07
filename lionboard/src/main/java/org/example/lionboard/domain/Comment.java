package org.example.lionboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 댓글(Comment) 엔티티
 * - 소프트 삭제 방식 사용 (delYn 플래그)
 */
@Entity
@Table(name = "comments")
@Getter @Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 댓글 내용
     */
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * 삭제 여부 (소프트 삭제)
     * - false: 활성 댓글
     * - true: 삭제된 댓글 (실제 데이터는 유지)
     */
    @Column(name = "del_yn", nullable = false)
    private Boolean delYn = false;

    /**
     * 댓글 작성일
     * - @Column(updatable = false): 수정 불가
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime regdate;

    /**
     * 게시글 (Post와 ManyToOne 관계)
     * - cascade = CascadeType.REMOVE: 게시글 삭제 시 댓글도 함께 삭제
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    /**
     * 작성자 (User와 ManyToOne 관계)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 댓글 생성을 위한 생성자
     */
    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
        this.delYn = false;
    }

    /**
     * 게시글 ID 반환 (post.getId() 호출)
     */
    public Long getPostId() {
        return post != null ? post.getId() : null;
    }

    /**
     * 작성자 ID 반환 (user.getId() 호출)
     */
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }
}