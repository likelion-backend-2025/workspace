package org.example.aopexam.exam;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    public void doSomething(){
        System.out.println("SimpleService doSomething run!!");
    }

    public void doSomething2(){
        System.out.println("SimpleService doSomething2 run!!");
    }

    public void hello(){
        System.out.println("Hello AOP");
    }
}
