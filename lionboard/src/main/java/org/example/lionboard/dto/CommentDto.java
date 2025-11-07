package org.example.lionboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 댓글 등록 요청 DTO
 * - userId는 @AuthenticationPrincipal로 자동 주입되므로 불필요
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    /**
     * 댓글 내용
     */
    private String content;

    /**
     * 게시글 ID
     */
    private Long postId;
}