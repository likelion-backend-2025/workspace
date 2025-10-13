package sample.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.Book;
import sample.bean.MyBean;
import sample.config.MyBeanConfig;

public class SpringExam01 {
    public static void main(String[] args) {
        //우리가 객체를 직접 사용하는 경우!!
//        MyBean bean = new MyBean();
//        bean.setName("kang");
//
//        System.out.println(bean.getName());

        //spring ioc container 를 이용해서  MyBean을 사용하고 싶어요~!!!
        //스프링이 제공하는 공장을 이용하기 위해서 우리가 뭘 해줘야 할까요?
        //스프링 공장에게 내가 어떤 객체를 사용하고 싶은지 알려줘야해요!!!
        //빈을 스프링 공장이 알도록 등록해야함!!! (1. xml 파일을 통해서  2. javaConfig 통해서 3. 애노테이션으로)

        System.out.println("공장세우기전!!");
        //공장은 뭐가중요해요???   어떤 빈을 만들건지가 중요해요!!!
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class);
        System.out.println("공장 세운 후!!");

        MyBean mybean = (MyBean) context.getBean("mybean");
        mybean.setName("carami");
        System.out.println(mybean.getName());

        //타입을 통해서도 주입 받을 수 있어요!!
//        MyBean mybean2 = context.getBean(MyBean.class);
        MyBean mybean2 = context.getBean("mybean2",MyBean.class);
        System.out.println(mybean2.getName());

        if(mybean == mybean2){
            System.out.println("mybean == mybean2 같은 인스턴스입니다. ");
        }

        MyBean mybean3 = context.getBean("mybean3",MyBean.class);
        System.out.println(mybean3.getName());
        System.out.println(mybean3.getCount());



        Book book = context.getBean("book",Book.class);  //lookup
        System.out.println(book);

    }
}
