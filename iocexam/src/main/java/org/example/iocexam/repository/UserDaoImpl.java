package org.example.iocexam.repository;

import org.example.iocexam.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public User getUser(String name) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }

    @Override
    public void addUser(User user) {
        System.out.println(user+"의 정보가 저장되었습니다.");
    }

    @Override
    public Optional<User> getOptionalUser(String name) {
        return Optional.empty();
    }
}
