package org.example.basicsecurity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/hello")
    public String hello() {
        log.info("UserController hello() 실행!!");
        System.out.println(Thread.currentThread().getName());

        userService.hellService();

        return "Hello World";
    }

    @GetMapping("/api/hello")
    public String hello2() {
        log.info("UserController hello() 실행!!");
        return "api/hello";
    }

    @GetMapping("/api/test")
    public String test() {
        log.info("UserController test() 실행!!");
        return "api/test";
    }
}
