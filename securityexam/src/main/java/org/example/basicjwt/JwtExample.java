package org.example.basicjwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtExample {
    public static void main(String[] args) {
        //1. 시크릿 키 (256 비트)
        //생성 방법 1 -  실행 시 마다 새로운 시크릿키를 생성함.
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println(secretKey);


        //생성 방법 2 - 내가 정해준 문자열을 바탕으로 생성  (문자열만 같으면, 같은 시크릿키를 얻어옴.)
        String secret = "abcdefghijklmnopqrstuvwxzy123456";   //나만 아는 문자열로!!
        SecretKey secretKey2 = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        System.out.println(secretKey2);

        //SecretKey 키는 jwt 토큰을 생성할 때 사용함!!!  (서명을 추가할 때!!)

        //2. JWT 생성
        String jwt = Jwts.builder()
                .issuer("lion-auth")//토큰 누가 발급한거야.
                .subject("carami")//loginid
                .audience().add("lion-server").add("lion-frontserver").and()
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //1시간
                .notBefore(new Date()) //지금부터가능
                .claim("role", "ADMIN")
                .claim("name", "kang")
                .signWith(secretKey2)
                .compact();

        System.out.println(jwt);



        //3. jwt 파싱 및 검증
        try {
            Claims claim = Jwts.parser()
                    .verifyWith(secretKey2)
                    .requireIssuer("lion-auth")
                    .requireAudience("lion-server")
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();


            //원하는 정보를 꺼내 보세요.
            System.out.println(claim.getIssuer());
            System.out.println(claim.getSubject());
            System.out.println(claim.get("role", String.class));
            System.out.println(claim.get("name", String.class));
            System.out.println(claim.getExpiration());
        }catch (IncorrectClaimException i){
            System.out.println("클레임 값이 일치하지 않아요.");
        }catch (JwtException j){
            System.out.println("토큰 검증 실패!!"+j.getMessage());
        }
    }
}
