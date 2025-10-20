package org.example.springjdbc.springdatajdbc;

import org.example.springjdbc.jdbc01.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }




    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return args -> {

//            repository.save(new User ("kkk","kkk@kkk.com"));

            User user = repository.findById(1L).get();
            System.out.println(user);

            repository.findAll().forEach(System.out::println);

            System.out.println("====================findByName=================");
            User userKang = repository.findByName("kang");
            System.out.println(userKang);


            System.out.println("=====================findByEmail================");
            User userCarami = repository.findByEmail("carami222@carami.com").orElse(null);
            System.out.println(userCarami);


            System.out.println("=======================abcUser================");
            repository.abcUser("test");

        };
    }
}
