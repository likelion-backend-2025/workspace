package org.example.todoapp.repository;


import org.example.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 완료 여부로 조회
    List<Todo> findByCompleted(Boolean completed);

    // 제목으로 검색 (부분 일치)
    List<Todo> findByTitleContaining(String keyword);
}