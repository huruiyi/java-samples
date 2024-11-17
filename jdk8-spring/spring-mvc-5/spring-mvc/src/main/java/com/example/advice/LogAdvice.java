package com.example.advice;

import java.util.Arrays;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {

    Logger logger = Logger.getLogger(Logger.class);

  @Pointcut("execution(public * com.example..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        logger.debug(joinPoint.getTarget() + " : " + joinPoint.getSignature());
        logger.debug("方法执行参数: " + Arrays.toString(joinPoint.getArgs()));
    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        Object result = null;
        try {
            result = pjp.proceed(pjp.getArgs());
            logger.debug("方法的执行结果：" + result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}

