package sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import sample.bean.Dice;
import sample.bean.Game;
import sample.bean.Player;

import java.util.List;
@Configuration
@Profile("dev")
@PropertySource({"classpath:game.properties"})
public class GameDevConfig {
    //Dice Bean 을 등록해볼까요?

    @Value("${face}")
    int face;

    @Bean
    public Dice dice(/*@Value("${face}") int face*/){
        return new Dice(face);
    }

    @Bean
    public Player kang(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("강경미");
        return player;
    }

    @Bean
    public Player kim(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("김둘리");
        return player;
    }

    @Bean
    public Player hong(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("홍길동");
        return player;
    }

    @Bean
    public Player lee(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("이효리");
        return player;
    }

    @Bean
    public Player park(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("박지성");
        return player;
    }

    @Bean
    public Game game(List<Player> players){
        return  new Game(players);
    }

}
