package org.example.restexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.restexam.domain.User;
import org.example.restexam.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){

        return ResponseEntity.ok(userService.getUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){

        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User createUser = userService.addUser(user);
        return ResponseEntity.ok(createUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id")Long id,@RequestBody User user){
        user.setId(id);
        User updateUser = userService.updateUser(user);
        return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
