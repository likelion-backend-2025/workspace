package org.example.lionboard.repository;

import org.example.lionboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Comment 엔티티의 데이터 액세스 인터페이스
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 특정 게시글의 댓글 목록 조회 (삭제되지 않은 것만, 작성일 오름차순)
     * - findBy: 조회 메소드
     * - Post_Id: Post 엔티티의 id 필드로 조회
     * - AndDelYnFalse: AND 조건으로 delYn이 false인 것만
     * - OrderByRegdateAsc: regdate 오름차순 정렬
     *
     * @param postId 게시글 ID
     * @return List<Comment>
     */
    List<Comment> findByPost_IdAndDelYnFalseOrderByRegdateAsc(Long postId);

    // 특정 게시글의 댓글 개수
    long countByIdAndDelYnFalse(Long postId);
}