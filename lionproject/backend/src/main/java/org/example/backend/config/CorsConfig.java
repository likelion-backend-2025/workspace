package org.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // 프론트엔드 도메인 허용 (Vite 기본 포트)
        config.addAllowedOrigin("http://localhost:5173");
         config.addAllowedOrigin("http://localhost:5174");
          config.addAllowedOrigin("http://localhost:5175");
           config.addAllowedOrigin("http://localhost:3000");
        
        // 허용할 HTTP 메서드
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        
        // 허용할 헤더
        config.addAllowedHeader("*");
        
        // 인증 정보 허용 (필요한 경우)
        config.setAllowCredentials(true);
        
        // 모든 경로에 CORS 설정 적용
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}

