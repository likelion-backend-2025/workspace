package org.example.securityexam4.config;

import lombok.RequiredArgsConstructor;
import org.example.securityexam4.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    //세션 관리자 빈!!!  현재 활성화 된 모든 세션을 추적하는 저장소 역할을 함.
    //동시 세션을 관리해야할 때 필수적
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    //이벤트 등록
    //세션이 생성되거나, 만료될 때 시큐리티에게 알려주는 역할을 함.
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,SessionRegistry sessionRegistry) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/user/regForm", "/user/userreg","/user/welcome","/user").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/shop/**").hasRole("USER")
                        .anyRequest().authenticated()
                );
        http
                .formLogin(form -> form
                        .loginPage("/user/loginform")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/user/welcome",true)
                        .permitAll()
                );
        http
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/user/welcome"));

        http
                .userDetailsService(customUserDetailsService);


        http
                .sessionManagement(session -> session
                    .maximumSessions(1)  //동시 접속 허용 개수
                        .maxSessionsPreventsLogin(false)  //디폴트 false - 먼저 로그인한 사용자가 차단.  //두번제 접속 로그인 안됨.
                        .sessionRegistry(sessionRegistry)
                );



        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
