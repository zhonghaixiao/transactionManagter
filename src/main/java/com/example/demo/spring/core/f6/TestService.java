package com.example.demo.spring.core.f6;

import com.example.demo.shiro.example.RequirePermission;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    @Log(title = "testTitle111", action = "log test111")
    @RequirePermission(permissions = {"per1", "per2"})
    public void test1(){

    }

}
