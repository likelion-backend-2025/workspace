package org.example.hellospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/hi")
    public String carami(){
        return "hi carami!!!";
    }

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to Spring Boot");
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }
}
