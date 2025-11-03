package org.example.aopexam.exam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopApplication {
    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(SimpleService service, AnotherService anotherService) {
        return args -> {
//            service.doSomething();
            service.doSomething2();

            System.out.println("=============================");

            anotherService.anotherMethod();
            
            System.out.println("=============================");
            service.hello();


        };
    }
}
