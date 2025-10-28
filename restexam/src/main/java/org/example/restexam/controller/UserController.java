package org.example.restexam.controller;

import org.example.restexam.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserController {
    //user에 대해서 crud 할수있는 메서드를 정의해 볼 수 있겠죠??
    ///users   -- get
    @GetMapping("/users")
    public List<User> getUsers(){

        return null;
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return null;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return null;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user){
        return null;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(){

    }
}
