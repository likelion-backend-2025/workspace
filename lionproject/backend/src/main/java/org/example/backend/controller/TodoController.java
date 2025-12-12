package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.dto.TodoRequest;
import org.example.backend.dto.TodoResponse;
import org.example.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    
    private final TodoService todoService;
    
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    /**
     * 모든 할 일 조회
     * GET /api/todos
     */
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        List<TodoResponse> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }
    
    /**
     * 특정 할 일 조회
     * GET /api/todos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        TodoResponse todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }
    
    /**
     * 할 일 생성
     * POST /api/todos
     */
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request) {
        TodoResponse createdTodo = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }
    
    /**
     * 할 일 수정
     * PUT /api/todos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequest request) {
        TodoResponse updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(updatedTodo);
    }
    
    /**
     * 완료 상태 토글
     * PATCH /api/todos/{id}/toggle
     */
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TodoResponse> toggleTodo(@PathVariable Long id) {
        TodoResponse toggledTodo = todoService.toggleTodo(id);
        return ResponseEntity.ok(toggledTodo);
    }
    
    /**
     * 할 일 삭제
     * DELETE /api/todos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}

