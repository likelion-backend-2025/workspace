package org.example.lionboard.security;



import lombok.RequiredArgsConstructor;
import org.example.lionboard.domain.User;
import org.example.lionboard.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security의 UserDetailsService 구현
 * - 로그인 시 사용자 정보를 데이터베이스에서 조회
 * - User 엔티티를 UserDetailsImpl로 감싸서 반환
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 사용자 이름(loginId)으로 사용자 정보를 조회
     * - Spring Security가 자동으로 호출하는 메소드
     * - User 엔티티를 조회하여 UserDetailsImpl 어댑터로 감싸서 반환
     *
     * @param username 로그인 ID
     * @return UserDetailsImpl 객체 (UserDetails 구현체)
     * @throws UsernameNotFoundException 사용자를 찾을 수 없는 경우
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "사용자를 찾을 수 없습니다: " + username));

        return new UserDetailsImpl(user);  // ⭐ User를 어댑터로 감싸서 반환
    }
}