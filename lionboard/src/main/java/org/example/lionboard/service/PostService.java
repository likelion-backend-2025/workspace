package org.example.lionboard.service;


import lombok.RequiredArgsConstructor;
import org.example.lionboard.domain.Post;
import org.example.lionboard.domain.User;
import org.example.lionboard.dto.PostDto;
import org.example.lionboard.dto.PostResponseDto;
import org.example.lionboard.exception.PostNotFoundException;
import org.example.lionboard.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시글 관리 서비스
 */
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 작성
     * - 작성자는 현재 로그인한 사용자
     *
     * @param postDto 게시글 정보 DTO
     * @param currentUser 현재 로그인한 사용자
     * @return 저장된 Post 엔티티
     */
    @Transactional
    public Post createPost(PostDto postDto, User currentUser) {
        Post post = new Post(
                postDto.getTitle(),
                postDto.getContent(),
                currentUser  // User 엔티티 전체 전달
        );
        return postRepository.save(post);
    }

    /**
     * 게시글 수정
     * - 작성자 본인만 수정 가능
     *
     * @param id 수정할 게시글 ID
     * @param postDto 수정할 게시글 정보
     * @param currentUser 현재 로그인한 사용자
     * @return 수정된 Post 엔티티
     * @throws PostNotFoundException 게시글을 찾을 수 없는 경우
     * @throws AccessDeniedException 작성자가 아닌 경우
     */
    @Transactional
    public PostResponseDto updatePost(Long id, PostDto postDto, User currentUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));

        if (!post.getUserId().equals(currentUser.getId())) {
            throw new AccessDeniedException("본인의 게시글만 수정할 수 있습니다.");
        }

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return PostResponseDto.from(updatedPost);
    }
    /**
     * 게시글 삭제
     * - 작성자 본인 또는 ADMIN 역할이면 삭제 가능
     *
     * @param id 삭제할 게시글 ID
     * @param currentUser 현재 로그인한 사용자
     * @throws PostNotFoundException 게시글을 찾을 수 없는 경우
     * @throws AccessDeniedException 권한이 없는 경우
     */
    @Transactional
    public void deletePost(Long id, User currentUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));

        // 관리자 권한 확인 (User의 roles를 직접 조회)
        boolean isAdmin = currentUser.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ADMIN"));

        // 작성자 본인 확인
        boolean isOwner = post.getUserId().equals(currentUser.getId());

        // 관리자이거나 작성자 본인인 경우에만 삭제 가능
        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("게시글 삭제 권한이 없습니다.");
        }

        postRepository.deleteById(id);
    }

    /**
     * 페이징된 게시글 목록 조회
     * - 최신 글 우선 정렬은 Controller에서 Pageable에 Sort 추가
     *
     * @param pageable 페이징 정보
     * @return 페이징된 게시글 목록
     */
    @Transactional(readOnly = true)
    public Page<PostResponseDto> getPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(PostResponseDto::from);
    }

    /**
     * 특정 게시글 조회
     *
     * @param id 조회할 게시글 ID
     * @return Post 엔티티
     * @throws PostNotFoundException 게시글을 찾을 수 없는 경우
     */
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));

        return PostResponseDto.from(post);
    }
}