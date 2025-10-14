package org.example.iocexam.controller;

import org.example.iocexam.domain.User;
import org.example.iocexam.service.UserService;

public class UserController {
    private UserService userService;

    public UserController() {
        System.out.println("UserController() 실행");
    }
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
}
