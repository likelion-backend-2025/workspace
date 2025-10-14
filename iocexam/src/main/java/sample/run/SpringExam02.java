package sample.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.bean.Dice;
import sample.bean.Game;
import sample.bean.Player;
import sample.config.GameConfig;

import java.util.Arrays;

public class SpringExam02 {
    public static void main(String[] args) {
        Dice dice = new Dice(6);
        Player p1 = new Player();
        p1.setName("jeong");
        p1.setDice(dice);
        Player p2 = new Player();
        p2.setName("kim");
        p2.setDice(dice);
        Game game2 = new Game(Arrays.asList(p1, p2));
        game2.play();


        ApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);
//        Dice dice = context.getBean("dice", Dice.class);
//        System.out.println(dice.getNumber());
//
//        Player kang = context.getBean("kang", Player.class);
//        kang.play();

        Game game = context.getBean(Game.class);
        game.play();
    }
}
