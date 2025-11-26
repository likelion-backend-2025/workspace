package org.example.dockerapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {
    @GetMapping("/")
    public String index() {
        return "Hello Dcoker!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hi!!!";
    }

    @GetMapping("/info" )
    public String info() {
        return "나는 carami 입니다.!";
    }
}
