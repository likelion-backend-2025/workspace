package org.example.lionboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class ErrorResponseBuilder {
    private HttpStatus status;
    private String message;
    private String path;

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public ErrorResponseBuilder status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ErrorResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponseBuilder path(String path) {
        this.path = path;
        return this;
    }
    public ResponseEntity<Map<String, Object>> build() {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);
        errorResponse.put("path", path);

        return ResponseEntity.status(status).body(errorResponse);
    }

    public Map<String, Object> buildMap() {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);
        errorResponse.put("path", path);
        return errorResponse;
    }
}