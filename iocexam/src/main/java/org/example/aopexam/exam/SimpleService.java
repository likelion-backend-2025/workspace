package org.example.aopexam.exam;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    public void doSomething(){
        System.out.println("SimpleService doSomething run!!");
    }

    public String doSomething2(){

        System.out.println("SimpleService doSomething2 run!!");
        System.out.println("SimpleService doSomething2 run!!");

        System.out.println("SimpleService doSomething2 run!!");
        System.out.println("SimpleService doSomething2 run!!");

        //예외 강제 발생 -- After trhowing 테스트를 위해서
//        if(1==1){
//            throw new RuntimeException("강제 발생");
//        }

        return "kang";

    }

    public void hello(){
        System.out.println("Hello AOP");
    }
}
