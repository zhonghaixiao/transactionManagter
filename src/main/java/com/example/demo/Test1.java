package com.example.demo;

import com.example.demo.shiro.factory.SimpleFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.IntStream;

public class Test1 {

    public static void main(String[] args){

         ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");

//        SimpleFactory simpleFactory = new SimpleFactory(true);
//        IntStream.range(1, 10).forEach((i) ->System.out.println(simpleFactory.getInstance()));
    }

}
