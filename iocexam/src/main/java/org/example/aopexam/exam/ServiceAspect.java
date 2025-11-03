package org.example.aopexam.exam;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ServiceAspect {
    @Pointcut("execution(* org.example.aopexam.exam.*Service.*(..))")
    public void pc(){}

    @Pointcut("execution(* hello())")
    public void helloPc(){}

    @Before("pc()")
    public void before(JoinPoint joinPoint){
        System.out.println("Before ============================="+joinPoint.getSignature().getName());
    }

    @Before("helloPc()")
    public void beforePc(JoinPoint joinPoint){
        System.out.println("hello ====================="+joinPoint.getSignature().getName());
    }

}
