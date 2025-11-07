package org.example.lionboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.lionboard.dto.CommentDto;
import org.example.lionboard.dto.CommentResponseDto;
import org.example.lionboard.security.UserDetailsImpl;
import org.example.lionboard.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 댓글 관리 Controller
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성
     * - 인증 필요
     * - 작성자는 현재 로그인한 사용자로 자동 설정
     *
     * @param postId 게시글 ID
     * @param commentDto 댓글 정보 DTO
     * @param userDetails 현재 로그인한 사용자 정보 (Spring Security가 자동 주입)
     * @return 생성된 CommentResponseDto (201 Created)
     */
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto comment = commentService.createComment(postId, commentDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    /**
     * 특정 게시글의 댓글 목록 조회
     * - 인증 필요
     * - 삭제되지 않은 댓글만 조회 (delYn = false)
     * - 작성일 오름차순 정렬
     *
     * @param postId 게시글 ID
     * @return 댓글 목록 (200 OK)
     */
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable("postId") Long postId) {
        List<CommentResponseDto> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    /**
     * 댓글 삭제 (소프트 삭제)
     * - 인증 필요
     * - 작성자 본인 또는 ADMIN 역할이면 삭제 가능 (Service에서 권한 체크)
     * - delYn을 true로 변경 (실제 데이터는 유지)
     *
     * @param postId 게시글 ID
     * @param id 삭제할 댓글 ID
     * @param userDetails 현재 로그인한 사용자 정보
     * @return 204 No Content
     */
    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("postId") Long postId,
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.softDeleteComment(id, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}