package org.example.basicjwt;

import io.jsonwebtoken.Claims;

import java.util.Arrays;

public class JwtTokenizerTest {
    public static void main(String[] args) {
        JwtTokenizer jwtTokenizer = new JwtTokenizer(
                "12345678901234567890123456789012",
                "12345678901234567890123456789012"
        ) ;

        String accessToken = jwtTokenizer.createAccessToken(
                1L,
                "carami@carami.com",
                "carami",
                "강경미",
                Arrays.asList("ROLE_ADMIN", "ROLE_USER")
        );
        System.out.println(accessToken);

        String refreshToken = jwtTokenizer.createRefreshToken(
                1L,
                "carami@carami.com",
                "carami",
                "강경미",
                Arrays.asList("ROLE_ADMIN", "ROLE_USER")
        );
        System.out.println(refreshToken);


        Claims claims = jwtTokenizer.parseAccessToken(accessToken);
        System.out.println("Access token: " + claims.get("email"));
        System.out.println("Access token: " + claims.get("userId"));

        Long userIdFromToken = jwtTokenizer.getUserIdFromToken(accessToken);
        System.out.println(userIdFromToken);


    }
}
