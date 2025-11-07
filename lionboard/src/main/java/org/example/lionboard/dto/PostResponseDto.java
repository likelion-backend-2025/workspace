package org.example.lionboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.lionboard.domain.Post;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime postedDate;
    private Long userId;
    private String userName;

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getPostedDate(),
                post.getUser().getId(),
                post.getUser().getName()
        );
    }
}