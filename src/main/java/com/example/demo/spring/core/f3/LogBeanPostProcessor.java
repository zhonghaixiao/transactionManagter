package com.example.demo.spring.core.f3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Component
public class LogBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        List<Class<?>> clazzes = getAllClasses(bean);
        for (Class<?> clazz: clazzes){
            initializeLog(bean, clazz);
        }
        return bean;
    }

    private List<Class<?>> getAllClasses(Object bean) {
        Class<? extends Object> clazz = bean.getClass();
        List<Class<?>> clazzes = new ArrayList<Class<?>>();
        while (clazz != null) {
            clazzes.add(clazz);
            clazz = clazz.getSuperclass();
        }

        Collections.reverse(clazzes);
        return clazzes;
    }

    private void initializeLog(Object bean, Class<? extends Object> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Logger.class) == null) {
                continue;
            }

            if (!field.getType().isAssignableFrom(Log.class)) {
                continue;
            }

            field.setAccessible(true);
            try {
                field.set(bean, LogFactory.getLog(clazz));
            } catch (Exception e) {
                throw new BeanInitializationException(String
                        .format("初始化logger失败!bean=%s;field=%s", bean, field));
            }

        }
    }

}
