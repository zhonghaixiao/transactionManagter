package com.example.demo.spring.core.f2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Component
public class MyListenerProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //获取类及其所有父类中定义的方法
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods != null) {
            for (Method method: methods){
                MyListener myListener = AnnotationUtils.findAnnotation(method, MyListener.class);
                if (myListener != null){
                    System.out.println("find one, method = " + method);
                }
            }
        }
        return bean;
    }
}
