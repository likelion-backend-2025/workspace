package org.example.swaggerexam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "사용자 정보 응답 DTO")
public class UserDto {
    @Schema(description = "사용자 고유 ID", example = "1")
    private Long id;
    @Schema(description = "사용자 이메일", example = "user@exam.com")
    private String email;
}
