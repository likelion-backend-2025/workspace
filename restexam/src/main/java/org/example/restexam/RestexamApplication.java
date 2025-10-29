package org.example.restexam;

import org.example.restexam.domain.User;
import org.example.restexam.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class RestexamApplication {

    @Bean
    CommandLineRunner run(UserRepository userRepository){
        return args->{
            List<User> all = userRepository.findAll();
            for(User user : all ){
                System.out.println(user.getName());
            }

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(RestexamApplication.class, args);
    }

}
