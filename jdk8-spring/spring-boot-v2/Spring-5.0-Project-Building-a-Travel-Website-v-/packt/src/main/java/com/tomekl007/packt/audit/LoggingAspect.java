package com.tomekl007.packt.audit;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

  static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

  @Before("execution(* com.tomekl007.packt.booking.BookingService.book(..))")
  public void logBookingRequest(JoinPoint joinPoint) {
    log.info("before booking request with arguments: {}", Arrays.toString(joinPoint.getArgs()));
  }
}
