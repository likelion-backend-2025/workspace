package org.example.restexam.service;

import org.example.restexam.domain.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User getUser(Long id);
    public User addUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long id);

}
