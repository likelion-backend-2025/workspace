package org.example.springdatajpa3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
//            userRepository.findById(3L).ifPresent(System.out::println);
//            userRepository.lionCustom();
//            userRepository.findUsersByName("c")
//                    .forEach(System.out::println);
            userRepository.findUsersDynamically("carami",null)
                    .forEach(System.out::println);

        };

    }
}
