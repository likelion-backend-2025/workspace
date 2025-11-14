package org.example.swaggerexam.service;

import org.springframework.transaction.annotation.Transactional;

public class UserService {

    //회원가입
    @Transactional
    public String register(String email, String password) {
        //매개변수로 들어온 이메일이 이미 존재 하는지 체크

        //존재하면  "이미 존재하는 아이디입니다" 리턴

        //존재하지 않으면 DB에 저장하고 "회원가입 성공"  리턴

        return "회원가입 성공";
    }
}
