package org.example.jwtexam.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

    @Test
    @DisplayName("사용자 권한 정보가 ROLE_ 와 함께 잘 생성되는지.. ")
    void testAuthorities(){
        //given
        List<String> roles = List.of("USER", "ADMIN");
        CustomUserDetails userDetails = new CustomUserDetails(
                "carami",
                "1234",
                "강경미",
                roles
        );

        //when
        List<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
        //then
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains("ROLE_USER"));
        assertTrue(authorities.contains("ROLE_ADMIN"));
    }
}