package org.example.aopexam.exam;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ServiceAspect {
    @Pointcut("execution(* org.example.aopexam.exam.*Service.*(..))")
    public void pc(){}

    @Pointcut("execution(String org.example.aopexam.exam.*Service.*(..))")
    public void pc2(){}

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

    @After("pc()")
    public void after(JoinPoint joinPoint){
        System.out.println("After============================="+joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "pc2()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, String result){
        System.out.println("AfterReturning=========================="+joinPoint.getSignature().getName());
        System.out.println("AfterReturning==========================result="+result);
    }

    @AfterThrowing(pointcut = "pc2()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        System.out.println("AfterThrowing=========================="+joinPoint.getSignature().getName());
        System.out.println("AfterThrowing==========================Exception value = "+ex.getMessage());
    }

    @Around("pc2()")
    public String around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around ==========Target이 실행되기 전에 해야할 일이 있다면 여기에 구현===================="+pjp.getSignature().getName());

        //이 메서드 윗쪽은 Target이 실행되기 전에 해야할 일 구현
        String returnValue =(String) pjp.proceed();//  실제 Target 을 호출!!!
//이 메서드 아래쪽에는 Target이 실행된 후 해야할 일 구현
        //리턴값을 가공 할 수도 있다.
        System.out.println("around ==========Target이 실행된 후에 해야할 일이 있다면 여기에 구현===================="+pjp.getSignature().getName());

        returnValue += "carami aop run!!";
        
        return returnValue;
    }

}
