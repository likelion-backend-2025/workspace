package org.example.lionboard.security;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.lionboard.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Spring Security의 UserDetails 구현체 (Adapter 패턴)
 * - User 엔티티를 감싸서 Spring Security가 요구하는 UserDetails 인터페이스를 구현
 * - User 엔티티는 순수 도메인 모델로 유지하고, Spring Security 의존성을 분리
 */
@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    /**
     * 실제 User 엔티티 (도메인 객체)
     */
    private final User user;

    /**
     * Spring Security가 사용자의 권한 목록을 가져오는 메소드
     * - Role 이름 앞에 "ROLE_" 접두사 추가 (Spring Security 규칙)
     * - 예: "USER" -> "ROLE_USER", "ADMIN" -> "ROLE_ADMIN"
     *
     * @return GrantedAuthority 컬렉션
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 사용자의 암호화된 비밀번호 반환
     * - Spring Security가 인증 시 사용
     *
     * @return 암호화된 비밀번호
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Spring Security가 사용자 식별에 사용하는 username 반환
     * - 우리 시스템에서는 loginId를 username으로 사용
     *
     * @return 로그인 ID
     */
    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    /**
     * 계정이 만료되지 않았는지 확인
     * - true 반환 = 계정이 유효함
     * - 실무에서는 User 엔티티에 expiredDate 필드를 추가하여 판단
     *
     * @return 계정 만료 여부 (true: 만료되지 않음)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠기지 않았는지 확인
     * - true 반환 = 계정이 잠기지 않음
     * - 실무에서는 User 엔티티에 locked 필드를 추가하여 판단
     *
     * @return 계정 잠김 여부 (true: 잠기지 않음)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호가 만료되지 않았는지 확인
     * - true 반환 = 비밀번호가 유효함
     * - 실무에서는 User 엔티티에 passwordExpiredDate 필드를 추가하여 판단
     *
     * @return 비밀번호 만료 여부 (true: 만료되지 않음)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 활성화되었는지 확인
     * - true 반환 = 계정이 활성화됨
     * - 실무에서는 User 엔티티에 enabled 필드를 추가하여 판단
     *
     * @return 계정 활성화 여부 (true: 활성화됨)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}