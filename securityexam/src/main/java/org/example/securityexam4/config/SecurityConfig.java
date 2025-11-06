package org.example.securityexam4.config;

import lombok.RequiredArgsConstructor;
import org.example.securityexam4.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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
                );



        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
