package org.example.lionboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lionboard.domain.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private Boolean delYn;
    private LocalDateTime regdate;
    private Long postId;
    private Long userId;
    private String userName;

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getDelYn(),
                comment.getRegdate(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getUser().getName()
        );
    }
}