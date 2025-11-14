package org.example.swaggerexam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.swaggerexam.dto.LoginRequestDto;
import org.example.swaggerexam.dto.LoginResponseDto;
import org.example.swaggerexam.dto.RegisterRequestDto;
import org.example.swaggerexam.dto.UserDto;
import org.example.swaggerexam.repository.UserRepository;
import org.example.swaggerexam.service.UserService;
import org.example.swaggerexam.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Autentication",description = "인증 관련 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Operation(
            summary = "환영인사",
            description = "인증하면 환영인사를 해줍니다."
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    //회원가입  /register
    @Operation(
            summary = "회원가입",
            description = "이메일과 비밀 번호를 입력하여 회원 가입 합니다."
    )
    //응답에 관련된 시나리오를 설명한다.
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "String", example = "회원가입 성공"))),
            @ApiResponse(responseCode = "400", description = "회원가입 실패",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "String", example = "회원가입 실패"))),

    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto requestDto){

        //여기 실제로 들어갈 로직은 나중에 여러분이 완성 하는 것으로!!

        String message = userService.register(requestDto.getEmail(), requestDto.getPassword());
        return ResponseEntity.ok(message);
    }

    //로그인  - login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto){
        //사용자 정보를 확인 하는 등의 코드는 나중에 여러분이 완성하는 것으로!

        Long id = 1L;
        String token = jwtUtil.generateToken(id);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    //로그아웃 - logout
    @Operation(summary = "로그아웃")
    @SecurityRequirement(name = "bearerAuth") // "bearerAuth" 보안 설정 적용
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Parameter(description = "JWT 인증 토큰", required = true, example = "Bearer eyJhbGciOiJIUzI....")
            @RequestHeader("Authorization") String authorizationHeader
    ){
        // "Bearer " 접두사 제거
        String token = authorizationHeader.substring(7);

        // 토큰 무효화
        jwtUtil.invalidateToken(token);

        return ResponseEntity.ok("로그아웃 성공");
    }

    //사용자 목록  - users
    @Operation(summary = "사용자 목록 조회", description = "사용자 목록을 페이지 단위로 조회합니다.")
    @SecurityRequirement(name = "bearerAuth") // "bearerAuth" 보안 설정 적용
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        // ...
        return ResponseEntity.ok(null);
    }

    //id에 해당하는 사용자 조회  - users/{id}
    @Operation(summary = "사용자 정보 조회", description = "사용자의 고유 ID를 이용하여 정보를 조회합니다.")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable("id") Long id) {
//        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(null);
    }



}
