package org.example.lionboard.service;


import lombok.RequiredArgsConstructor;
import org.example.lionboard.domain.Role;
import org.example.lionboard.domain.User;
import org.example.lionboard.dto.UserDto;
import org.example.lionboard.exception.DuplicateUserException;
import org.example.lionboard.exception.UserNotFoundException;
import org.example.lionboard.repository.RoleRepository;
import org.example.lionboard.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 관리 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     * - 비밀번호 암호화
     * - 기본 "USER" 역할 자동 부여
     *
     * @param userDto 회원 정보 DTO
     * @return 저장된 User 엔티티
     * @throws DuplicateUserException 중복된 로그인 ID인 경우
     */
    @Transactional
    public User createUser(UserDto userDto) {
        // 1. 중복 체크
        if (userRepository.existsByLoginId(userDto.getLoginId())) {
            throw new DuplicateUserException("이미 존재하는 로그인 ID입니다.");
        }

        // 2. 사용자 생성 (비밀번호 암호화)
        User user = new User(
                userDto.getName(),
                userDto.getLoginId(),
                passwordEncoder.encode(userDto.getPassword()),  // BCrypt 암호화
                userDto.getEmail()
        );

        // 3. 기본 역할(USER) 부여
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    // USER 역할이 없으면 생성
                    Role newRole = new Role("USER");
                    return roleRepository.save(newRole);
                });

        user.addRole(userRole);

        // 4. 저장
        return userRepository.save(user);
    }

    /**
     * 회원 삭제
     * - 본인 또는 관리자만 삭제 가능 (Controller에서 권한 체크)
     *
     * @param id 삭제할 회원의 ID
     * @throws UserNotFoundException 사용자를 찾을 수 없는 경우
     */
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("사용자를 찾을 수 없습니다.");
        }
        userRepository.deleteById(id);
    }
}