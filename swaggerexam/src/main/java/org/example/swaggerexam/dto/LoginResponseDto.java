package org.example.swaggerexam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "로그인 응답 DTO (엑세스토큰정보)")
public class LoginResponseDto {
    @Schema(description = "액세스 토큰 ", example = "eydkfjdkdkfjdkfdjfiedkjfk.....")
    private String accessToken;

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
