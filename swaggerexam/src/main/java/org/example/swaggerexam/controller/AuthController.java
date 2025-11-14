package org.example.swaggerexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.swaggerexam.repository.UserRepository;
import org.example.swaggerexam.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    //회원가입  /register


    //로그인  - login

    //로그아웃 - logout

    //사용자 목록  - users

    //id에 해당하는 사용자 조회  - users/{id}



}
