package org.example.aopexam.exam2.service;

import org.example.aopexam.exam2.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDTO createUser(String name, String email){
        System.out.println("사용자 생성중");
        return new UserDTO(1L, name, email);
    }
}
