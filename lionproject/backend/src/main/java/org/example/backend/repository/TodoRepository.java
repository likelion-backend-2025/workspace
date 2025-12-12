package org.example.backend.repository;

import org.example.backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // JpaRepository가 기본 CRUD 메서드를 자동으로 제공합니다:
    // - findAll(): List<Todo>
    // - findById(Long id): Optional<Todo>
    // - save(Todo todo): Todo
    // - deleteById(Long id): void
    // - count(): long
    // 등등...
}

