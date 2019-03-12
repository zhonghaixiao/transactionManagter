package com.example.demo.spring.core.f4;

import com.example.demo.activity.service.ActivityService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

//@Component
public class MyCustomPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ActivityService){
            System.out.println("创建代理，实现事务");
            Object res = Proxy.newProxyInstance(getClass().getClassLoader(), bean.getClass().getInterfaces(), new MyCustomProxy(bean));
            return res;
        }
        return bean;
    }
}
