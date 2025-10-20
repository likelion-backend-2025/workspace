package org.example.springjdbc.jdbc01;

import java.util.List;

public interface UserDao {
    void insertUser(User user);
    List<User> findAllUsers();
}
