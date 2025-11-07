package org.example.lionboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.lionboard.domain.User;
import org.example.lionboard.dto.UserDto;
import org.example.lionboard.security.UserDetailsImpl;
import org.example.lionboard.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 관리 Controller
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입
     * - 인증 없이 접근 가능 (SecurityConfig에서 permitAll 설정)
     * - 비밀번호는 Service에서 자동 암호화됨
     * - 기본 "USER" 역할 자동 부여
     *
     * @param userDto 회원 정보 DTO
     * @return 생성된 User 엔티티 (201 Created)
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {

        User user = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * 회원 삭제
     * - 인증 필요
     * - 본인 계정 또는 ADMIN 권한만 삭제 가능 (TODO: 권한 체크 구현)
     *
     * @param id 삭제할 회원 ID
     * @param userDetails 현재 로그인한 사용자 정보 (Spring Security가 자동 주입)
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}