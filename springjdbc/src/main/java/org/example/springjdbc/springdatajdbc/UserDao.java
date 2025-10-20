package org.example.springjdbc.springdatajdbc;

import java.util.Optional;

public interface UserDao {
    public Optional<User> abcUser(String email);
}
