package org.example.aopexam.exam;

import org.springframework.stereotype.Component;


@Component
public class UserController{
    @TrackTime
    public void addUser(){
        System.out.println("addUser");
    }
}
