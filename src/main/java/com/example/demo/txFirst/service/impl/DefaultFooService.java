package com.example.demo.txFirst.service.impl;

import com.example.demo.txFirst.domain.Foo;
import com.example.demo.txFirst.service.FooService;

/**
 * Created by zhong on 2018/9/1.
 */
public class DefaultFooService implements FooService {

    @Override
    public Foo getFoo(String fooName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Foo getFoo(String fooName, String barName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertFoo(Foo foo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFoo(Foo foo) {
        throw new UnsupportedOperationException();
    }
}
