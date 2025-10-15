package org.example.iocexam;

import org.example.iocexam.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocexamApplication implements CommandLineRunner {


    public static void main(String[] args) {

        SpringApplication.run(IocexamApplication.class, args);
    }

    @Autowired
    UserController userController;

    @Override
    public void run(String... args) throws Exception {
        userController.joinUser();
    }
}

