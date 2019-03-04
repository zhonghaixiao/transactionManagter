package com.example.demo;

import com.example.demo.shiro.factory.SimpleFactory;

import java.util.stream.IntStream;

public class Test1 {

    public static void main(String[] args){
        SimpleFactory simpleFactory = new SimpleFactory(true);
        IntStream.range(1, 10).forEach((i) ->System.out.println(simpleFactory.getInstance()));
    }

}
