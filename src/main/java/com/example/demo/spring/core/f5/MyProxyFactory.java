package com.example.demo.spring.core.f5;

import org.springframework.beans.factory.FactoryBean;

public class MyProxyFactory<T> implements FactoryBean<T> {

    private Class<T> interfaceClass;
    public Class<T> getInterfaceClass() {
        return interfaceClass;
    }
    public void setInterfaceClass(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
    @Override
    public T getObject() throws Exception {
        return (T) new MyProxy().bind(interfaceClass);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    public boolean isSingleton() {
        // 单例模式
        return true;
    }

}