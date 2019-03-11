package com.example.demo.restclient.version1;

import com.example.demo.spring.core.f2.MyListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class RestClientPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        RestClient restClient = AnnotationUtils.findAnnotation(beanClass, RestClient.class);
        if (restClient != null){
            System.out.println("创建jdk代理类");
            return new JdkRestClientProxy().getProxy(beanClass);
        }
        if (beanClass.isAssignableFrom(TestClient.class)){
            System.out.println("find TestClient");
        }
        return null;
    }

    //    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        //获取类及其所有父类中定义的方法
//        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
//        if (methods != null) {
//            for (Method method: methods){
//                MyListener myListener = AnnotationUtils.findAnnotation(method, MyListener.class);
//                if (myListener != null){
//                    System.out.println("find one, method = " + method);
//                }
//            }
//        }
//        return bean;
//    }

}
