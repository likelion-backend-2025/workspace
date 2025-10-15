package org.example.iocexam.config;

import org.example.iocexam.controller.UserController;
import org.example.iocexam.repository.UserCaramiDaoImpl;
import org.example.iocexam.repository.UserDao;
import org.example.iocexam.repository.UserDaoImpl;
import org.example.iocexam.service.UserService;
import org.example.iocexam.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan(basePackages = "org.example.iocexam")
@Configuration
public class UserConfig {

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }
    @Bean
    public UserDao userCaramiDao(){
        return new UserCaramiDaoImpl();
    }

    @Bean
    public UserService userService(UserDao userCaramiDao) {
        return new UserServiceImpl(userCaramiDao);
    }

    @Bean
    public UserController userController(UserService userService){
        return new UserController(userService);
    }
}
