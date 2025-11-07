package org.example.lionboard.repository;

import org.example.lionboard.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Role 엔티티의 데이터 액세스 인터페이스
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * 역할 이름으로 조회
     * @param name 역할 이름 (예: "USER", "ADMIN")
     * @return Optional<Role>
     */
    Optional<Role> findByName(String name);
}