package org.example.lionboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 가입 요청 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    /**
     * 사용자 이름 (실명)
     */
    private String name;

    /**
     * 로그인 ID (중복 불가)
     */
    private String loginId;

    /**
     * 비밀번호 (평문, 서버에서 암호화됨)
     */
    private String password;

    /**
     * 이메일 주소
     */
    private String email;
}