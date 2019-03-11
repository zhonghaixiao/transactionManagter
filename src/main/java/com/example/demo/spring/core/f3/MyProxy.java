package com.example.demo.spring.core.f3;

import org.apache.commons.logging.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {

    //使用logger的注解来简化定义，并自动注入

    @Logger
    private static Log log;

    private Object obj;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("-------------------" + "bean 名称为【" + name + "】方法为【" + method.getName() + "】-------------"
                + obj.getClass());
        return method.invoke(obj, args);
    }

    public void printDetail(String detail) {
        log.error(detail);
    }

}
