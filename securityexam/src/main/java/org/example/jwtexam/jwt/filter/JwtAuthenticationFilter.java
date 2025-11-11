package org.example.jwtexam.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //목적은 무엇일까요?
//    요청에 포함된 JWT(AccessToken) 을 검증하고, 토큰이 유효하다면?? (인증된 사용자라면??)
    //Spring security의 SecurityContextHolder에다가 사용자 인증정보 (Authentication) 를 등록해야겠네요. 

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {




        //중요!!!
        filterChain.doFilter(request,response);
    }
//필터는 무엇하는 객체인가요?

}
