package com.example.demo.shiro.factory;

import java.util.Random;

public class SimpleFactory extends AbstractFactory<Integer> {

    private Random random = new Random();

    public SimpleFactory(boolean isSingleton){
        setSingleton(isSingleton);
    }

    @Override
    protected Integer createInstance() {
        return random.nextInt(10);
    }
}
