package sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import sample.bean.Dice;
import sample.bean.Game;
import sample.bean.Player;

import java.util.List;

@PropertySource({"classpath:game.properties"})
public class GameConfig {
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
        player.setName("Kang");
        return player;
    }

    @Bean
    public Player kim(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("kim");
        return player;
    }

    @Bean
    public Player hong(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("hong");
        return player;
    }

    @Bean
    public Player lee(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("lee");
        return player;
    }

    @Bean
    public Player park(Dice dice){
//        Player player = new Player(dice);
        Player player = new Player();
        player.setDice(dice);
        player.setName("park");
        return player;
    }

    @Bean
    public Game game(List<Player> players){
        return  new Game(players);
    }

}
