package org.example.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

//            userRepository.findByName("kang").forEach(user -> {log.info(user.toString());});
//
//            List<User> userList = userRepository.findByName("carami");
//            for(User user : userList){
//                log.info(user.toString());
//            }

//            userRepository.findByEmail("kang@kang.com").
//                    forEach(user->log.info(user.toString()));

//            userRepository.selectUser("kang").forEach(user->log.info(user.toString()));

//            int i = userRepository.deleteUserByEmail("kang@kang.com");
//            System.out.println(i+"건삭제!!");

//            int i = userRepository.updateUserByEmail(3L, "carami@exam.com");
//            User user = userRepository.findById(3L).get();
//            System.out.println(user);

            Pageable pageable = PageRequest.of(0, 10);
            List<User> caramis = userRepository.selectUserByEmail("carami",pageable);
            for(User user : caramis) {
                log.info(user.toString());
            }
        };
    }
}
