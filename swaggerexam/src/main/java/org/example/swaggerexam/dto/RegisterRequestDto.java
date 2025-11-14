package org.example.swaggerexam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원가입 요청 DTO")
public class RegisterRequestDto {
    @Schema(description = "사용자 이메일", required = true, example = "user@user.com")
    private String email;
    @Schema(description = "사용자 비밀번호",example = "password1234")
    private String password;
}
