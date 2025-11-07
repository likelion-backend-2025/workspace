package org.example.lionboard.service;

import lombok.RequiredArgsConstructor;
import org.example.lionboard.domain.Comment;
import org.example.lionboard.domain.Post;
import org.example.lionboard.domain.User;
import org.example.lionboard.dto.CommentDto;
import org.example.lionboard.dto.CommentResponseDto;
import org.example.lionboard.exception.CommentNotFoundException;
import org.example.lionboard.exception.PostNotFoundException;
import org.example.lionboard.repository.CommentRepository;
import org.example.lionboard.repository.PostRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 댓글 관리 서비스
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * 댓글 작성
     * - 작성자는 현재 로그인한 사용자
     *
     * @param postId 게시글 ID
     * @param commentDto 댓글 정보 DTO
     * @param currentUser 현재 로그인한 사용자
     * @return 저장된 CommentResponseDto
     * @throws PostNotFoundException 게시글을 찾을 수 없는 경우
     */
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentDto commentDto, User currentUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));

        Comment comment = new Comment(
                commentDto.getContent(),
                post,
                currentUser
        );

        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDto.from(savedComment);
    }

    /**
     * 특정 게시글의 댓글 목록 조회
     * - 삭제되지 않은 것만 (delYn = false)
     * - 작성일 오름차순 정렬
     *
     * @param postId 게시글 ID
     * @return 댓글 목록
     */
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPost_IdAndDelYnFalseOrderByRegdateAsc(postId);
        return comments.stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 댓글 소프트 삭제
     * - 작성자 본인 또는 ADMIN 역할이면 삭제 가능
     * - 실제 삭제가 아닌 delYn을 true로 변경
     *
     * @param id 삭제할 댓글 ID
     * @param currentUser 현재 로그인한 사용자
     * @throws CommentNotFoundException 댓글을 찾을 수 없는 경우
     * @throws AccessDeniedException 권한이 없는 경우
     */
    @Transactional
    public void softDeleteComment(Long id, User currentUser) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("댓글을 찾을 수 없습니다."));

        boolean isAdmin = currentUser.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ADMIN"));

        boolean isOwner = comment.getUserId().equals(currentUser.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("댓글 삭제 권한이 없습니다.");
        }

        comment.setDelYn(true);
        commentRepository.save(comment);
    }
}