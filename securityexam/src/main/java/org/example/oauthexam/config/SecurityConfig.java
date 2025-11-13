package org.example.oauthexam.config;

import lombok.RequiredArgsConstructor;
import org.example.oauthexam.security.CustomOAuth2AuthenticationSuccessHandler;
import org.example.oauthexam.service.SocialUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SocialUserService socialUserService;
    private final CustomOAuth2AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http, CorsConfigurationSource configurationSource) throws Exception {

        return http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/","/userregform").permitAll()
                        .requestMatchers("/oauth2/**","/login/oauth2/code/github","/registerSocialUser","/saveSocialUser").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf->csrf.disable())
                .formLogin(form->form
                        .loginPage("/loginform")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .cors(cors->cors.configurationSource(configurationSource))
                .httpBasic(httpbasic->httpbasic.disable())
                .oauth2Login(oaut2->oaut2
                        .loginPage("/loginform")
                        .failureUrl("/loginFailure")
                        .userInfoEndpoint(userInfo->userInfo
                                .userService(this.oAuth2UserService())
                        )
                        .successHandler(customOAuth2AuthenticationSuccessHandler)
                )
                .logout(logout->logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )

                .build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return oauth2UserRequest ->{
            OAuth2User oAuth2User = delegate.loadUser(oauth2UserRequest);

            //소셜 로그인이 될 때 할일..
            String provider = oauth2UserRequest.getClientRegistration().getRegistrationId();
            String socialId = String.valueOf(oAuth2User.getAttributes().get("id"));
            String username = String.valueOf(oAuth2User.getAttributes().get("login"));
            String email = String.valueOf(oAuth2User.getAttributes().get("email"));
            String avatarUrl = String.valueOf(oAuth2User.getAttributes().get("avatarUrl"));

            socialUserService.saveOrUpdateUser(socialId,provider,username,email,avatarUrl);
            return oAuth2User;
        };
    }


    @Bean
    public CorsConfigurationSource configurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setAllowedMethods(List.of("GET","POST","DELETE","OPTIONS"));
        source.registerCorsConfiguration("/**",config);
        return source;
    }


}
