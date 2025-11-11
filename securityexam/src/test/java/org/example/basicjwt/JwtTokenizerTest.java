package org.example.basicjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
public class JwtTokenizerTest {
    @Autowired
    private JwtTokenizer jwtTokenizer;


    @Test
    @DisplayName("액세스 토큰 생성 테스트")
    void testCreateAccessToken() {
        String token = jwtTokenizer.createAccessToken(
                1L,
                "user@example.com",
                "User",
                "username",
                List.of("USER")
        );

        assertThat(token).isNotEmpty();
    }

    @Test
    @DisplayName("액세스 토큰 파싱 테스트")
    void testParseAccessToken() {
        String token = jwtTokenizer.createAccessToken(
                1L,
                "user@example.com",
                "User",
                "username",
                List.of("USER")
        );

        Claims claims = jwtTokenizer.parseAccessToken(token);

        assertThat(claims.getSubject()).isEqualTo("username");
        assertThat(claims.get("userId", Long.class)).isEqualTo(1L);
        assertThat(claims.get("roles", List.class)).contains("USER");
    }

    @Test
    @DisplayName("Bearer 토큰에서 사용자 ID 추출 테스트")
    void testGetUserIdFromToken() {
        String token = jwtTokenizer.createAccessToken(
                1L,
                "user@example.com",
                "User",
                "username",
                List.of("USER")
        );

        Long userId = jwtTokenizer.getUserIdFromToken("Bearer " + token);

        assertThat(userId).isEqualTo(1L);
    }

    @Test
    @DisplayName("리프레시 토큰 생성 및 파싱 테스트")
    void testCreateAndParseRefreshToken() {
        String token = jwtTokenizer.createRefreshToken(
                2L,
                "refresh@example.com",
                "RefreshUser",
                "refresh",
                List.of("USER")
        );

        Claims claims = jwtTokenizer.parseRefreshToken(token);

        assertThat(claims.getSubject()).isEqualTo("refresh");
        assertThat(claims.get("userId", Long.class)).isEqualTo(2L);
    }

    @Test
    @DisplayName("잘못된 토큰 파싱 시 예외 발생 테스트")
    void testParseInvalidToken() {
        String invalidToken = "invalid.token.here";

        assertThatThrownBy(() -> jwtTokenizer.parseAccessToken(invalidToken))
                .isInstanceOf(JwtException.class);
    }
}
