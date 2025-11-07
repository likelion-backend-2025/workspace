package org.example.lionboard.repository;

import org.example.lionboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Post 엔티티의 데이터 액세스 인터페이스
 * - JpaRepository: 기본 CRUD + 페이징/정렬 기능
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 게시글 목록 조회 (페이징)
     * - JpaRepository가 제공하는 findAll(Pageable) 사용
     * - 최신 글 우선 정렬은 Service에서 Pageable에 Sort 추가
     */

    /**
     * 특정 사용자의 게시글 목록 조회 (페이징)
     * @param userId 사용자 아이디
     * @param pageable 페이징 정보
     * @return Page<Post>
     */
    Page<Post> findById(Long userId, Pageable pageable);

    /**
     * 제목으로 게시글 검색 (페이징)
     * @param keyword 검색 키워드
     * @param pageable 페이징 정보
     * @return Page<Post>
     */
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}