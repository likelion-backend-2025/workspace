package org.example.securityexam;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //1.  사용자가 아무것도 안 했을 때 스프링부트의 시큐리티는 아래와 같은 설정으로 동작된다.
//        return http
//                .authorizeHttpRequests(auth ->auth
//                        .anyRequest()  //모든 요청
//                        .authenticated()  //인증을 요구
//                )
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .csrf(Customizer.withDefaults())
//                .build();

        return http
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/hi","/hello","/test/*","/loginForm","/fail").permitAll()  //여기 지정된 페이지는 인증 없이 요청 가능
                        .anyRequest().authenticated() //나머지 모든 요청에 대해서는 인증을 요구 하겠다.
                )
                .formLogin(formLogin -> formLogin
//                        .loginPage("/loginForm")  //시큐리티가 제공하는 로그인폼 페이지가 아니고 사용자가 원하는 페이지로 사용하도록 설정.
                                .defaultSuccessUrl("/success",true) //로그인에 성공하면 어디로 갈까요?
                                .loginProcessingUrl("/login_proc")
                                .usernameParameter("email")
                                .passwordParameter("passpw")
//                                .failureUrl("/fail")
                                .successHandler((request, response, authentication) -> {
                                    //인증에 성공 했을 때 내가 하고 싶은 일을 직접 구현 함!!
                                    System.out.println("로그인 성공!!!" + authentication.getName());
                                    response.sendRedirect("/info");

                                })
                                .failureHandler((request, response, exception) -> {
                                    System.out.println("로그인 실패!!"+exception.getMessage());
                                    response.sendRedirect("/hello");
                                })

                )
                .logout(logout -> logout
//                        .logoutUrl("/logout_carami")   //post 전용!!   Get방식이 허용하는 것을 위험하다고 판단한다.
                        .logoutSuccessUrl("/hello")
                                .addLogoutHandler((request, response, authentication) -> {
                                    //로그아웃할때 어떤일을 하면 좋을까요??
                                    System.out.println("로그아웃!!  세션, 쿠키도 삭제해 주면 좋겠죠!!");
                                    request.getSession(false).invalidate(); //세션 삭제!!
                                })
                                .deleteCookies("JSESSIONID")
                )
                .build();
    }
}
