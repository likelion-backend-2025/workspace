package sample.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.Book;
import sample.bean.MyBean;
import sample.bean.Player;
import sample.config.MyBeanConfig;

public class SpringExam03 {
    public static void main(String[] args) {

        System.out.println("공장세우기전!!");
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class);
        System.out.println("공장 세운 후!!");


        MyBean bean = context.getBean(MyBean.class);
        bean.setName("knag");
        System.out.println(bean.getName());


        Player player = context.getBean(Player.class);
        player.setName("knag");
        player.play();


    }
}
