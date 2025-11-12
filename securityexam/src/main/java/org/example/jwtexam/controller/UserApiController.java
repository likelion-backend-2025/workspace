package org.example.jwtexam.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
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
            log.info("bindingResult.hasErrors() ==========");
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



    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response){
        //1. 쿠키에서 리프레시토큰을 추출..
        String token = getRefreshTokenFromCookies(request);
        //2. 토큰이 없다면??  400 에러반환
        if(token == null){
            return ResponseEntity.badRequest().body("리프레시 토큰이 없어요.");
        }

        try {
            //3. 토큰을 검증및 파싱   (예외 처리)
            Claims claims = jwtTokenizer.parseRefreshToken(token);
            //4. 우리 디비에 저장된 리프레시토큰과 가지고 온 리프레시 토큰이 일치하는지 확인!!   (반드시 해야할 일이지만,  우리 예제에서는 선택!!)
            RefreshToken dbToken = refreshTokenService
                    .findRefreshToken(token).orElseThrow(() -> new IllegalArgumentException("토큰이 없어요."));
            if(dbToken == null || !token.equals(dbToken.getToken())){

                log.warn("사용자가 보낸 리프레시 토큰이 DB 와 달라요!!");
//               이 시점에, DB의 토큰을 삭제하는 등 조치를 취할 수 있어요.
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("리프레시 토큰이 이상해요. ");
            }

            //5. 사용자 정보 추출
            Long userId = claims.get("userId", Long.class);
            User user = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾지 못했어요."));


            //6. 새로운 엑세스 토큰 생성
            List<String> roles = claims.get("roles", List.class);
            String accessToken = jwtTokenizer.createAccessToken(userId, user.getEmail(), user.getName(), user.getUsername(), roles);


            //7. 엑세스 토큰을 쿠키에 설정
            addTokenCookie(response,"accessToken",accessToken,jwtTokenizer.getAccessTokenExpireCount());

            //8. 응답으로 보내주고!!
            UserLoginResponseDto responseDto = UserLoginResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(token)
                    .name(user.getName())
                    .userId(user.getId())
                    .build();

            return ResponseEntity.ok(responseDto);
        }catch (SignatureException | MalformedJwtException | IllegalArgumentException e) {
            // 잘못된 토큰 (서명 불일치, 형식 오류) 처리
            log.error("Invalid refresh token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        } catch (ExpiredJwtException e) {
            //만료된 토큰 처리
            log.warn("Expired refresh token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
        } catch (Exception e) {
            // 기타 서버 오류 처리
            log.error("Refresh token error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }

    private void addTokenCookie(HttpServletResponse httpServletResponse, String name, String value, Long expireCount){
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);   //자바스크립트에서 접근 불가.
        cookie.setPath("/");
        cookie.setMaxAge(Math.toIntExact(expireCount / 1000));
        httpServletResponse.addCookie(cookie);    //https 를 사용한다라고 하면 cookie.setSecure(true) 반드시 필요.
    }


    //토큰을 얻어오는 메서드
    private String getToken(HttpServletRequest request) {

        //헤더에서 access토큰을 찾아요.  (헤더로 accesstoken이 들어왔을경우!!
        String authorization = request.getHeader("Authorization");
        if(StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }

        //accessToken이 쿠키를 통해서 들어왔을 때.
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if("accessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
//'refreshToken' 쿠키에서만 토큰을 추출하는  메서드
    private String getRefreshTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
