package org.example.aopexam.exam;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("@annotation(org.example.aopexam.exam.TrackTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint)
            throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            // 대상 메서드 실행
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();

            System.out.println(String.format(
                    "[Performance] %s.%s() executed in %dms",
                    className, methodName, executionTime
            ));
        }
    }
}