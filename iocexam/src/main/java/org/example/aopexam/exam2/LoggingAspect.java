package org.example.aopexam.exam2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)  // 실행 순서 지정
public class LoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* org.example.aopexam.exam2.service.*.*(..))")
    public void loggingPointCut() {}

    @Before("loggingPointCut()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("logBefore");
        logger.info("Before executing: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(
            pointcut = "loggingPointCut()",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}",
                joinPoint.getSignature().toShortString(),
                result
        );
    }

    @AfterThrowing(
            pointcut = "loggingPointCut()",
            throwing = "exception"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.error("Exception in {}: {}",
                joinPoint.getSignature().toShortString(),
                exception.getMessage()
        );
    }
}