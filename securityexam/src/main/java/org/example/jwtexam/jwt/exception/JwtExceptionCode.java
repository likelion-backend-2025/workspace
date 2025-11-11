package org.example.jwtexam.jwt.exception;

import lombok.Getter;

import java.util.Arrays;

public enum JwtExceptionCode {
    UNKNOWN_ERROR("UNKNOWN_ERROR", "알수없어요ㅠㅠ"),
    NOT_FOUND_TOKEN("NOT_FOUND_TOKEN","Headers에서 토큰 형식의 값을 찾지 못했어요."),
    INVALID_TOKEN("INVALID_TOKEN","유효하지 않은 토큰"),
    EXPIRED_TOKEN("EXPIRED_TOKEN","기간이 만료된 토큰"),
    UNSUPPORTED_TOKEN("UNSUPPORTED_TOKEN","지원하지 않는 토큰");


    JwtExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private String code;
    @Getter
    private String message;

//    코드를 기반으로 Enum 상수를 찾는 정적 메서드
    public static JwtExceptionCode findByCode(String code) {
        return Arrays.stream(JwtExceptionCode.values())//모든 Enum 상수를 스트림으로 변환 시켜서
                .filter(c ->c.getCode().equals(code))
                .findFirst()
                .orElse(UNKNOWN_ERROR);

    }

}
