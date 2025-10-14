package org.example.iocexam.service;

import org.example.iocexam.domain.User;
import org.example.iocexam.repository.UserDao;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {}

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void joinUser(User user) {
        userDao.addUser(user);
    }
}
