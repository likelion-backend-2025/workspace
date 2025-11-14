package org.example.swaggerexam.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class JwtUtil {
    private static final String SECRET = "my-secret-key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
    private final ConcurrentHashMap<String, Boolean> invalidTokens = new ConcurrentHashMap<>();

    // JWT 생성
    public String generateToken(Long userId) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    // JWT 검증 및 사용자 ID 반환
    public Long validateToken(String token) {
        try {
            if (invalidTokens.containsKey(token)) {  // 로그아웃된 토큰인지 확인
                return null;
            }
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return Long.parseLong(decodedJWT.getSubject()); // userId 반환
        } catch (JWTVerificationException | NumberFormatException e) {
            return null;
        }
    }

    // JWT 무효화 (로그아웃)
    public void invalidateToken(String token) {
        invalidTokens.put(token, true);
    }


}
