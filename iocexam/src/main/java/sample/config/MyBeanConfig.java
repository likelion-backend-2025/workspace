package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import sample.bean.Book;
import sample.bean.MyBean;

public class MyBeanConfig {
    //스프링 공장에게 나 어떤 빈을 관리할건지 알려줌.
    //xml
    //<bean id="mybean" class="sample.bean.MyBean"/>

    //기본 설정은 싱글톤으로 생성된다.
    @Bean
//    @Scope("prototype")
    public MyBean mybean(){
        return new MyBean();
    }

    @Bean
    public MyBean mybean2(){
        return new MyBean();
    }

    @Bean
    public MyBean mybean3(){
        MyBean mybean = new MyBean();
        mybean.setName("kang kyung mi");
        mybean.setCount(10);
        return mybean;
    }

    @Bean
    public Book book(){
        return new Book("happy spring","carami","124bo22");
    }
}
