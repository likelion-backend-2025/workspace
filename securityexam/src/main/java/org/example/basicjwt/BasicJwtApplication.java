package org.example.basicjwt;

import io.jsonwebtoken.Claims;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BasicJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasicJwtApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(JwtTokenizer jwtTokenizer) {
        return args -> {
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

        };
    }
}
