package com.example.demo.spring.core.f3;

import org.apache.commons.logging.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component
public class ProxyBeanPostProcesser implements BeanPostProcessor {
    private Map map = new ConcurrentHashMap(100);

    //使用logger的注解来简化定义，并自动注入

    @Logger
    private static Log log;

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        MyProxy proxy = new MyProxy();

        if (bean.toString().contains("Proxy")) {
            log.info(beanName + "为代理类,不进行再次代理!");
            return bean;
        }


        //……
        //可以加一些其他条件，过滤掉你不想代理的bean
        //……省略部分代码


        if (map.get(beanName) != null) {
            log.info(beanName + "已经代理过,不进行再次代理!");
            return map.get(beanName);
        }
        proxy.setObj(bean);
        proxy.setName(beanName);
        Class[] iterClass = bean.getClass().getInterfaces();
        if (iterClass.length > 0) {
            Object proxyO = Proxy.newProxyInstance(bean.getClass().getClassLoader(), iterClass, proxy);
            map.put(beanName, proxyO);
            return proxyO;
        } else {
            log.info(beanName + "必须实现接口才能被代理。");
            return bean;
        }
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}