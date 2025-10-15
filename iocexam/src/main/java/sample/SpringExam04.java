package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import sample.bean.Game;

@SpringBootApplication
public class SpringExam04 implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringExam04.class, args);
    }

    @Autowired
    Game game;
    @Override
    public void run(String... args) throws Exception {
        game.play();
    }

    //스프링부트가 동작한 후에 실행하고자 하는 코드가 있었을때...
//    @Bean
//    public CommandLineRunner commandLineRunner(Game game) {
//        return args -> {
//            game.play();
//        };
//    }
}

