package com.example.demo.salary.infrastructure.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
//@Aspect
public class LoggingAspect {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.demo.salary.api.SalaryService.pay(..))")
    public void logSalaryRequest(JoinPoint joinPoint) {
        log.info("before salary request with arguments: " + Arrays.toString(joinPoint.getArgs()));
    }
}
