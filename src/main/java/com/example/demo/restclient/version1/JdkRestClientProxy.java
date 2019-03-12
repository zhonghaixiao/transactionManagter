package com.example.demo.restclient.version1;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class JdkRestClientProxy implements InvocationHandler {

    public <T> T getProxy(Class clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk rest client method invoke");
        return method.invoke(args);
    }
}
