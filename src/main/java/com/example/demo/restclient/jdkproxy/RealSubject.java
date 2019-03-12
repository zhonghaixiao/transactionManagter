package com.example.demo.restclient.jdkproxy;

public class RealSubject implements Subject{

    @Override
    public void doSomething() {
        System.out.println("RealSubject do something");
    }

}
