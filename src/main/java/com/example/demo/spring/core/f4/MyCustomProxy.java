package com.example.demo.spring.core.f4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyCustomProxy implements InvocationHandler {
    private Object target;
    public MyCustomProxy(Object target){
        super();
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isNeed(method)){
            beginTransaction(proxy);
        }
        Object res = method.invoke(target, args);
        if (isNeed(method)){
            endTransaction();
        }
        return res;
    }

    private void beginTransaction(Object o){
        System.out.println("-----开始事务--------");
    }

    private void endTransaction(){
        System.out.println("-----结束事务--------");
    }

    private boolean isNeed(Method method){
        if (method.getAnnotation(MyTransaction.class) != null) {
            return true;
        }
        return false;
    }


}
