package org.example.jwtexam.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jwtexam.domain.RefreshToken;
import org.example.jwtexam.domain.Role;
import org.example.jwtexam.domain.User;
import org.example.jwtexam.dto.UserLoginResponseDto;
import org.example.jwtexam.jwt.util.JwtTokenizer;
import org.example.jwtexam.security.dto.UserLoginDto;
import org.example.jwtexam.service.RefreshTokenService;
import org.example.jwtexam.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenService refreshTokenService;


    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    //기존에 로그인처리는 시큐리티가 하고 있었는데,  이부분을 우리가 하는 것으로 구현!!
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDto  userLoginDto,
                                   BindingResult bindingResult,
                                   HttpServletResponse response){
        //1. 입력값이 유효한가?
        if(bindingResult.hasErrors()){
            //Validation 실패 했을 때..   에러메시지들을 바디에 담아서 반환
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        //2. 입력한 사용자가 우리 시스템에 있는가?  비밀번호는 맞나??    -- 비밀번호를 비교하기위해서는??
        User user = userService.findByUsername(userLoginDto.getUsername()).orElse(null);
        if(user == null || !passwordEncoder.matches(userLoginDto.getPassword(),user.getPassword())){
            //존재하는 user가 없거나,  비밀번호가 맞지 않는다면 401 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        //3. 토큰들을 발급.
        //3-1. 토큰 생성을 위해서 Role 정보를 추출
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        //3-2. Access Token, Refresh Token 발급
        String accessToken = jwtTokenizer.createAccessToken(user.getId(),user.getEmail(),user.getName(),user.getUsername(),roles);
        String refreshToken = jwtTokenizer.createRefreshToken(user.getId(),user.getEmail(),user.getName(),user.getUsername(),roles);

        //4. 리프레시토큰 DB 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setUserId(user.getId());

        refreshTokenService.addRefreshToken(refreshTokenEntity);


        //토큰을 쿠키에 구울것인지,  응답으로만 보낼건지..  (반드시 두가지를 다 해야하는 것은 아니다. )
        addTokenCookie(response,"accessToken",accessToken, jwtTokenizer.getAccessTokenExpireCount());
        addTokenCookie(response,"refreshToken", refreshToken, jwtTokenizer.getRefreshTokenExpireCount());


        //응답생성.
        UserLoginResponseDto loginResponseDto = UserLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .name(user.getName())
                .build();


        return ResponseEntity.ok(loginResponseDto);
    }

    private void addTokenCookie(HttpServletResponse httpServletResponse, String name, String value, Long expireCount){
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);   //자바스크립트에서 접근 불가.
        cookie.setPath("/");
        cookie.setMaxAge(Math.toIntExact(expireCount / 1000));
        httpServletResponse.addCookie(cookie);    //https 를 사용한다라고 하면 cookie.setSecure(true) 반드시 필요.
    }
}
