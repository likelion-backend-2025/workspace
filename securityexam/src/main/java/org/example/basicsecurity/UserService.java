package org.example.basicsecurity;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void hellService(){
        System.out.println("UserService   helloService()  실행!!!");
        System.out.println(Thread.currentThread().getName());
    }
}
