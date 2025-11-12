package org.example.jwtexam.jwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jwtexam.jwt.exception.JwtExceptionCode;
import org.example.jwtexam.jwt.token.JwtAuthenticationToken;
import org.example.jwtexam.jwt.util.JwtTokenizer;
import org.example.jwtexam.security.CustomUserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//OncePerRequestFilter 이 필터는 전체 요청중에 단 한 번 만 실행되요.    --  일반필터는 foward,include,error 등 상황에 여러번 호출 될 수 있다.
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenizer  jwtTokenizer;
    //목적은 무엇일까요?
//    요청에 포함된 JWT(AccessToken) 을 검증하고, 토큰이 유효하다면?? (인증된 사용자라면??)
    //Spring security의 SecurityContextHolder에다가 사용자 인증정보 (Authentication) 를 등록해야겠네요. 

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //해야할일
        // 주요목적은 JWT 토큰을 검증하고, 토큰이 유효하면 Spring security 의 SecurityContextHolder 에다가
        //사용자 인증 정보 (Authntication) 을 등록한다.

        //1. 토큰을 얻어온다.
        String token = getToken(request);

        //2. 토큰을 파싱해서 필요한 정보를 찾아서 Authentication 객체로 만들어서 SecurityContextHolder에다가 담는일 까지 하면 되겠네요..
        if(StringUtils.hasText(token)){
            try{
                getAuthentication(token);
            }catch (ExpiredJwtException e){
                request.setAttribute("exception", JwtExceptionCode.EXPIRED_TOKEN.getCode());
                log.error("Expired Token : {}",token,e);
                throw new BadCredentialsException("Expired token exception", e);
            }catch (UnsupportedJwtException e){
                request.setAttribute("exception", JwtExceptionCode.UNSUPPORTED_TOKEN.getCode());
                log.error("Unsupported Token: {}", token, e);
                throw new BadCredentialsException("Unsupported token exception", e);
            } catch (MalformedJwtException e) {
                request.setAttribute("exception", JwtExceptionCode.INVALID_TOKEN.getCode());
                log.error("Invalid Token: {}", token, e);
                throw new BadCredentialsException("Invalid token exception", e);
            } catch (IllegalArgumentException e) {
                request.setAttribute("exception", JwtExceptionCode.NOT_FOUND_TOKEN.getCode());
                log.error("Token not found: {}", token, e);
                throw new BadCredentialsException("Token not found exception", e);
            } catch (Exception e) {
                log.error("JWT Filter - Internal Error: {}", token, e);
                throw new BadCredentialsException("JWT filter internal exception", e);
            }
        }


        //중요!!!
        filterChain.doFilter(request,response);
    }
    //토큰을 파싱해서 필요한 정보를 찾아서 Authentication 객체로 만들어서 SecurityContextHolder에다가 담는 메서드
    private void getAuthentication(String token){
        Claims claims = jwtTokenizer.parseAccessToken(token);
        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        String name = claims.get("name", String.class);
        String email = claims.get("email", String.class);
        //권한 정보를 시큐리티가 원하는 형식으로 바꿔주기위함.
        List<GrantedAuthority> authorities = getAuthorities(claims);

        //위에서 얻어온 정보를 UserDetails 에 담아줘야겠죠?
        CustomUserDetails customUserDetails = new CustomUserDetails(username, "", name,
                authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(authoritiy ->authoritiy.replace("ROLE_",""))
                        .collect(Collectors.toList()));

        Authentication authentication = new JwtAuthenticationToken(authorities, customUserDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    //권한정보를 List<String> 으로 받아오는데 시큐리티는 List<GrantedAuthority> 형태로 사용하기 원하므로.. 변환해줌.
    private List<GrantedAuthority> getAuthorities(Claims claims){
        List<String> roles = (List<String>)claims.get("roles");
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
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

}
