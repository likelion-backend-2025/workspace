package org.example.springjdbc.jdbc01;

import java.util.List;

public interface UserDao {
    void insertUser(User user);
    List<User> findAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);
}
