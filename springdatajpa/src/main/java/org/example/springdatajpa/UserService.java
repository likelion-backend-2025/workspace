package org.example.springdatajpa;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final  UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
}
