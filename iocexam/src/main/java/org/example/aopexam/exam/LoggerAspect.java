package org.example.aopexam.exam;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggerAspect {

    @Before("execution(* org.example.aopexam.exam.*Service.*(..))")   //pointcut
    public void logging(){
//        실제 코드는 더 복잡하게 동작하겠죠??
        System.out.println("로그를 남깁니다.");  //advice
    }
}
