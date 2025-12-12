package org.example.backend.service;

import org.example.backend.dto.TodoRequest;
import org.example.backend.dto.TodoResponse;
import org.example.backend.entity.Todo;
import org.example.backend.exception.ResourceNotFoundException;
import org.example.backend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoService {
    
    private final TodoRepository todoRepository;
    
    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    /**
     * 모든 할 일 조회
     */
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * ID로 할 일 조회
     */
    @Transactional(readOnly = true)
    public TodoResponse getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("할 일을 찾을 수 없습니다. ID: " + id));
        return convertToResponse(todo);
    }
    
    /**
     * 할 일 생성
     */
    public TodoResponse createTodo(TodoRequest request) {
        Todo todo = new Todo();
        todo.setText(request.getText());
        todo.setCompleted(request.getCompleted() != null ? request.getCompleted() : false);
        
        Todo savedTodo = todoRepository.save(todo);
        return convertToResponse(savedTodo);
    }
    
    /**
     * 할 일 수정
     */
    public TodoResponse updateTodo(Long id, TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("할 일을 찾을 수 없습니다. ID: " + id));
        
        if (request.getText() != null) {
            todo.setText(request.getText());
        }
        if (request.getCompleted() != null) {
            todo.setCompleted(request.getCompleted());
        }
        
        Todo updatedTodo = todoRepository.save(todo);
        return convertToResponse(updatedTodo);
    }
    
    /**
     * 할 일 삭제
     */
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("할 일을 찾을 수 없습니다. ID: " + id);
        }
        todoRepository.deleteById(id);
    }
    
    /**
     * 완료 상태 토글
     */
    public TodoResponse toggleTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("할 일을 찾을 수 없습니다. ID: " + id));
        
        todo.setCompleted(!todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return convertToResponse(updatedTodo);
    }
    
    /**
     * Todo 엔티티를 TodoResponse DTO로 변환
     */
    private TodoResponse convertToResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getText(),
                todo.getCompleted(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}

