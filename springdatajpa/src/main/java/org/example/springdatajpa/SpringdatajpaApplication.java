package org.example.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringdatajpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {

//user 추가
//            User kanguser = new User("kang","kang@kang.com");
//            userRepository.save(kanguser);
//
//            log.info("user 추가 :: "+ kanguser);

            userRepository.findByName("kang").forEach(user -> {log.info(user.toString());});

            List<User> userList = userRepository.findByName("carami");
            for(User user : userList){
                log.info(user.toString());
            }
        };
    }
}
