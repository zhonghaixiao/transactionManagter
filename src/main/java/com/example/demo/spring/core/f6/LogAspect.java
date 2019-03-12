package com.example.demo.spring.core.f6;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(Log)")
    public void loginPointCut(){}

    @AfterReturning("loginPointCut() && @annotation(l)")
    public void doLog(Log l){
        log.info("test log!!!!!!!!!!!!!!!!!!");
        log.info("title = " + l.title());
        log.info("action = " + l.action());
    }

}
