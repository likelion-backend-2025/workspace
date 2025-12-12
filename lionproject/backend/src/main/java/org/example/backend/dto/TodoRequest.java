package org.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoRequest {
    @NotBlank(message = "할 일 내용은 필수입니다.")
    @Size(max = 500, message = "할 일 내용은 500자 이하여야 합니다.")
    private String text;
    
    private Boolean completed;
    
    // 기본 생성자
    public TodoRequest() {
    }
    
    // 전체 생성자
    public TodoRequest(String text, Boolean completed) {
        this.text = text;
        this.completed = completed;
    }
    
    // Getter와 Setter
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Boolean getCompleted() {
        return completed;
    }
    
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}

