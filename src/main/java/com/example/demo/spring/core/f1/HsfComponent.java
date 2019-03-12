package com.example.demo.spring.core.f1;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)//用于class，interface
@Retention(RetentionPolicy.RUNTIME)
public @interface HsfComponent {

    boolean registerBean() default false;

}
