package org.example.iocexam.repository;

import org.example.iocexam.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    public User getUser(String name);
    public List<User> getUsers();
    public void addUser(User user);
    public Optional<User> getOptionalUser(String name);
}
