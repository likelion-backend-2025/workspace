package org.example.iocexam.controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.iocexam.domain.User;
import org.example.iocexam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//@Controller
public class UserController {
    private UserService userService;

    public UserController() {
        System.out.println("UserController() 실행");
    }
//    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        System.out.println("UserController(UserService) 실행!");
    }

    public void joinUser(){

        //실제 서비스가 동작하고 있다면...   사용자가 브라우저에 입력한 정보가 들어오겠죠??
        User user = new User();
        user.setName("kang");
        user.setPassword("123");
        user.setEmail("kang@kang.com");

        userService.joinUser(user);

    }

    @PostConstruct
    public void init(){
        System.out.println("UserController 생성 후!! 할일 추가!!!");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("UserController 가 소멸되기 전에 할일 추가!@@");
    }
}
