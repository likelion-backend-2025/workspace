package org.example.restexam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ErrorTestController {
    @GetMapping("/api/etest")
    public ResponseEntity<String> test(){
        throw new RuntimeException("test");
    }

    @GetMapping("/api/ntest")
    public String test2(@RequestParam(name = "id", required = false) Long id){
        if(id == null){
            throw new RuntimeException("test");
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user 를 찾지 못했어요. ");
    }
}
