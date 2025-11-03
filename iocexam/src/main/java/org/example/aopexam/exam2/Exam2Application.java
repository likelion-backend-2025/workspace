package org.example.aopexam.exam2;

import org.example.aopexam.exam2.dto.UserDTO;
import org.example.aopexam.exam2.service.UserService;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Exam2Application {
    public static void main(String[] args) {
        SpringApplication.run(Exam2Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserService userService) {
        return args -> {

            System.out.println("정상실행");
            UserDTO userKang = userService.createUser("kang", "kang@exam.com");
            System.out.println("result ::"+ userKang);
        };
    }
}
