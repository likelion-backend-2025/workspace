package org.example.oauthexam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.oauthexam.entity.Role;
import org.example.oauthexam.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
@Getter
@Setter
public class SocialUserRequestDto {
    @NotBlank(message = "Provider 는 필수 값입니다.")
    private String provider;
    private String socialId;
    private String name;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private String username;
    private String uuid;


    public User toSocialEntity(PasswordEncoder encoder, Set<Role> defaultRoles) {
        // 소셜 로그인 사용자는 비밀번호를 사용하지 않으므로
        // not null 제약을 지키기 위해 임의 문자열을 인코딩하여 저장
        String encodedPassword = encoder.encode(UUID.randomUUID().toString());

        return User.builder()
                .username(this.username)
                .name(this.name)
                .email(this.email)
                .socialId(this.socialId)
                .provider(this.provider)
                .password(encodedPassword)
                .roles(defaultRoles)
                .build();
    }


    public User toSocialEntity(PasswordEncoder encoder,
                               Function<SocialUserRequestDto, String> usernameGenerator,
                               Set<Role> defaultRoles) {

        String resolvedUsername = usernameGenerator.apply(this);

        // 소셜 로그인 사용자는 실제 로그인에 비밀번호를 쓰지 않으므로 안전한 임의 문자열을 인코딩해 저장
        // 비밀번호 칼럼이 not null 이므로 빈 문자열 대신 난수값을 인코딩하여 보관
        String randomSecret = UUID.randomUUID().toString();
        String encoded = encoder.encode(randomSecret);

        return User.builder()
                .username(resolvedUsername)
                .name(Objects.requireNonNullElse(this.name, resolvedUsername))
                .email(Objects.requireNonNullElse(this.email, resolvedUsername + "@example.local"))
                .socialId(this.socialId)
                .provider(this.provider)
                .password(encoded)
                .roles(defaultRoles)
                .build();
    }

}
