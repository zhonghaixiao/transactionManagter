package com.example.demo.shiro.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ShiroAspect {

    @Pointcut("@target(RequirePermission)")
    public void annotatedClass(){}

    @Pointcut("@annotation(RequirePermission))")
    public void annotatedMethod(){}

    @Before("(annotatedMethod() || annotatedClass()) && @annotation(rp)")
    public void checkPermission(RequirePermission rp){
        System.out.println("current permissions = " + Arrays.asList(rp.permissions()));
    }

}
