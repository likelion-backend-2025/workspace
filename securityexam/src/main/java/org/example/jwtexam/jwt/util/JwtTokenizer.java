package org.example.jwtexam.jwt.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.example.jwtexam.jwt.exception.JwtExceptionCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtTokenizer {
    private final byte[] accessSecret;
    private final byte[] refreshSecret;

    private final Long accessTokenExpireCount;
    private final Long refreshTokenExpireCount;
/*
jwt:
  secretKey: 12345678901234567890123456789012
  refreshKey: abcdefghijklmnopqrstuvwxzy123456
  access-expiration-ms: 1800000     #30 * 60 * 1000
  refresh-expiration-ms: 604800000  # 7일
 */
    public JwtTokenizer(@Value("${jwt.secretKey}")String accessSecret,
                        @Value("${jwt.refreshKey}")String refreshSecret,
                        @Value("${jwt.access-expiration-ms}")String accessTokenExpireCount,
                        @Value("${jwt.refresh-expiration-ms}")String refreshTokenExpireCount) {
        this.accessSecret  = accessSecret.getBytes(StandardCharsets.UTF_8);
        this.refreshSecret = refreshSecret.getBytes(StandardCharsets.UTF_8);
        this.accessTokenExpireCount = Long.parseLong(accessTokenExpireCount);
        this.refreshTokenExpireCount = Long.parseLong(refreshTokenExpireCount);
    }

    //ACCESS TOKEN 생성
    public String createAccessToken(Long id,
                                    String email,
                                    String name,
                                    String username,
                                    List<String> roles) {
        return createToken(id,email,name,username,roles,accessTokenExpireCount,accessSecret );
    }
    //REFRESH TOKEN 생성
    public String createRefreshToken(Long id,
                                     String email,
                                     String name,
                                     String username,
                                     List<String> roles) {
        return createToken(id,email,name,username,roles,refreshTokenExpireCount,refreshSecret );
    }

    private String createToken(Long id,
                               String email,
                               String name,
                               String username,
                               List<String> roles,
                               Long expire,
                               byte[] secret
    ) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expire);
        return Jwts.builder()
                .subject(username)
                .claim("email",email)
                .claim("userId",id)
                .claim("name",name)
                .claim("roles",roles)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigingKey(secret))
                .compact();
    }

    private SecretKey getSigingKey(byte[] secretKey){
        return Keys.hmacShaKeyFor(secretKey);
//        return new SecretKeySpec(secretKey, "HmacSHA256");
    }

    //토큰을 파싱하는 메서드
    private Claims parseToken(String token, byte[] secret) {
        return Jwts.parser()
                .verifyWith(getSigingKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    //AcessToken 파싱
    public Claims parseAccessToken(String accessToken){
        return parseToken(accessToken, accessSecret);
    }
    //RefreshToken 파싱
    public Claims parseRefreshToken(String refreshToken){
        return parseToken(refreshToken, refreshSecret);
    }

    //토근에서 id 값만 빠르게 꺼내고 싶다면?
    //Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLqsJXqsr3rr7giLCJlbWFpbCI6ImNhcmFtaUBjYXJhbWkuY29tIiwidXNlcklkIjoxLCJuYW1lIjoiY2FyYW1pIiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJpYXQiOjE3NjI3NTYwMDEsImV4cCI6MTc2Mjc1NzgwMX0.gHoznGRXjFrOEToduhAYtGz7tz6v4NvKFB7UExo9vVg
    public Long getUserIdFromToken(String token){
        if(token == null || !token.startsWith("Bearer ")){
            throw new IllegalArgumentException("잘못된 형식 입니다. ");
        }

        try {
            String jwt = token.substring(7); //"Bearer " 제거
            Claims claims = parseToken(jwt, accessSecret);
            return claims.get("userId", Long.class);
        }catch(ExpiredJwtException e){
            log.warn("만료된 Access 토큰 : {}", e.getMessage());
            throw new RuntimeException(JwtExceptionCode.EXPIRED_TOKEN.getMessage());
        }catch(SignatureException | MalformedJwtException e){
            log.warn("유효하지 않은 토큰 : {}",e.getMessage());
            throw  new RuntimeException(JwtExceptionCode.INVALID_TOKEN.getMessage());
        }catch(Exception e){
            log.warn("JWT 파싱중 발생한 알수없는 오류 : {}",e.getMessage());
            throw new RuntimeException(JwtExceptionCode.UNKNOWN_ERROR.getMessage());
        }
    }

    public Long getAccessTokenExpireCount() {
        return accessTokenExpireCount;
    }

    public Long getRefreshTokenExpireCount() {
        return refreshTokenExpireCount;
    }
}
