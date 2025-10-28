package org.example.restexam.controller;

import org.example.restexam.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
//    @ResponseBody
    @GetMapping("/hi")
    public String hi() {
        return "hi rest controller";
    }

    @GetMapping("/user")
    public User user(){
        return new User(1L,"carami","carami@nate.com");
    }

    @GetMapping("/greeting")
    public Map<String, String> greet(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name) {

        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name + "!");
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }
}
