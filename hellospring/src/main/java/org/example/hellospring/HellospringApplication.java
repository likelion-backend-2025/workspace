package org.example.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HellospringApplication {
    @GetMapping("/carami")
    public String hello(){
        return "Hello Spring boot!!";
    }

    @GetMapping("/spirng")
    public String spring(){
        return "Hello carami!!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HellospringApplication.class, args);
    }

}
