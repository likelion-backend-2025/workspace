package org.example.oauthexam.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.oauthexam.entity.SocialLoginInfo;
import org.example.oauthexam.entity.User;
import org.example.oauthexam.service.SocialLoginInfoService;
import org.example.oauthexam.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final SocialLoginInfoService socialLoginInfoService;


    private String extractProviderFromUri(String uri){
        ///login/oauth2/code/github
        if(uri == null || uri.isBlank()){
            return null;
        }
        int idx = uri.indexOf("/login/oauth2/code/");
        if (idx == -1) return null;

        String provider = uri.substring(idx + "/login/oauth2/code/".length());
        if (provider.endsWith("/")) {
            provider = provider.substring(0, provider.length() - 1);
        }
        return provider;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //요청정보로부터 provider 를 얻어온다.
        //redirect-uri  : "/login/oauth2/code/{registrationid}

        String requestURI = request.getRequestURI();
        String provider = extractProviderFromUri(requestURI);

        //잘못된 요청이므로. 문제 발생
        if(provider == null){
            response.sendRedirect("/");
            return;
        }



        //Authentication 으로 부터 정보를 꺼낼 수 있을거예요.
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) token.getPrincipal();

        //깃헙 속성에서 id, name  을 추출
        String socialId = String.valueOf(oauthUser.getAttribute("social_id"));
        //소셜에서 정보가 비공개 일때..  정보가 없을 수 있어요.
//        "name" 없다면..  "login"
//        name 이라는 속성이 존재하지 않는다면.. login 이라고 속성을 줘서 값을 꺼내는데...
//        login 이라는 속성도 없다면 "user"라고 보내줘 .
        String name = String.valueOf(oauthUser.getAttributes().
                getOrDefault("name", oauthUser.getAttributes()
                        .getOrDefault("login","user")));

        // 기존 회원이야?  왜??  - 우리 어플리케이션에 처음 가입한 사용자라면 추가 정보를 받고 싶었어요.
        Optional<User> foundUser = userService.findByProviderAndSocialId(provider, socialId);

        //기본사용자라면, User에 정보가 있었을 거예ㅛㅇ.
        if(foundUser.isPresent()){
            User user = foundUser.get();

            // 우리 서비스에 맞게 교체
            CustomUserDetails customUserDetails = new CustomUserDetails(
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getRoles()
                            .stream()
                            .map(role->"ROLE_"+role.getName())
                            .collect(Collectors.toList())
            );

            Authentication newAuth =
                    new UsernamePasswordAuthenticationToken(customUserDetails, null,customUserDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(newAuth);
            response.sendRedirect("/welcome");
            return;
        }

        // 우리 어플리케이션에 처음 방문한 사용자라면 최소 정보만 저장한 후 추가 정보입력 화면으로 이동!!
        //추가정보입력 화면으로 가기전에 socialLoginInfo 에 정보를 저장해 놓음으로서...   정해진 시간보다 늦게 요청하면 오류 발생을 위해서 정보를 저장.
        SocialLoginInfo socialLoginInfo = socialLoginInfoService.saveSocialLoginInfo(provider, socialId);

        String redirect = "/registerSocialUser?provider="+provider
                +"&socialId="+url(socialId)
                +"&name="+url(name)
                +"&uuid"+url(socialLoginInfo.getUuid());

        response.sendRedirect(redirect);

    }

    private String url(String s){
        return URLEncoder.encode(s==null?"":s, StandardCharsets.UTF_8);
    }
}
