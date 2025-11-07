package org.example.lionboard.repository;

import org.example.lionboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User 엔티티의 데이터 액세스 인터페이스
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 로그인 ID로 사용자 조회
     * - Spring Security 인증 시 사용
     * @param loginId 로그인 ID
     * @return Optional<User>
     */
    Optional<User> findByLoginId(String loginId);

    /**
     * 로그인 ID 중복 체크
     * - 회원가입 시 사용
     * @param loginId 로그인 ID
     * @return true: 존재함, false: 존재하지 않음
     */
    boolean existsByLoginId(String loginId);
}