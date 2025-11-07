package org.example.lionboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.lionboard.domain.Post;
import org.example.lionboard.dto.PostDto;
import org.example.lionboard.dto.PostResponseDto;
import org.example.lionboard.security.UserDetailsImpl;
import org.example.lionboard.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 게시글 관리 Controller
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 작성
     * - 인증 필요
     * - 작성자는 현재 로그인한 사용자로 자동 설정
     *
     * @param postDto 게시글 정보 DTO
     * @param userDetails 현재 로그인한 사용자 정보 (Spring Security가 자동 주입)
     * @return 생성된 Post 엔티티 (201 Created)
     */
    @PostMapping
    public ResponseEntity<Post> createPost(
            @RequestBody PostDto postDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Post post = postService.createPost(postDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    /**
     * 게시글 수정
     * - 인증 필요
     * - 작성자 본인만 수정 가능 (Service에서 권한 체크)
     *
     * @param id 수정할 게시글 ID
     * @param postDto 수정할 게시글 정보
     * @param userDetails 현재 로그인한 사용자 정보
     * @return 수정된 Post 엔티티 (200 OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable("id") Long id,
            @RequestBody PostDto postDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto post = postService.updatePost(id, postDto, userDetails.getUser());
        return ResponseEntity.ok(post);
    }

    /**
     * 게시글 삭제
     * - 인증 필요
     * - 작성자 본인 또는 ADMIN 역할이면 삭제 가능 (Service에서 권한 체크)
     *
     * @param id 삭제할 게시글 ID
     * @param userDetails 현재 로그인한 사용자 정보
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(id, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }

    /**
     * 페이징된 게시글 목록 조회
     * - 인증 필요
     * - 최신 글이 먼저 표시 (postedDate 내림차순)
     *
     * @param page 페이지 번호 (기본값: 0)
     * @param size 페이지 크기 (기본값: 10)
     * @return 페이징된 게시글 목록 (200 OK)
     */
    @GetMapping
    public ResponseEntity<Page<PostResponseDto>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("postedDate").descending());
        Page<PostResponseDto> posts = postService.getPosts(pageable);
        return ResponseEntity.ok(posts);
    }

    /**
     * 특정 게시글 조회
     * - 인증 필요
     *
     * @param id 조회할 게시글 ID
     * @return Post 엔티티 (200 OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable("id") Long id) {
        PostResponseDto post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }
}
