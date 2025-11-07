package org.example.lionboard.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시글 등록/수정 요청 DTO
 * - userId는 @AuthenticationPrincipal로 자동 주입되므로 불필요
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    /**
     * 게시글 제목
     */
    private String title;

    /**
     * 게시글 내용
     */
    private String content;
}
