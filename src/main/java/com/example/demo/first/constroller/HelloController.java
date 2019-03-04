package com.example.demo.first.constroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2018/9/1.
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("getUser")
    public Result<UserData> getUserDataById(@RequestParam("userId") String userId){
        if ("1001".equals(userId)){
            return Result.ok(
                    UserData.builder().name("zhong").age(26).address("jiangsu").build());
        }
        if ("1002".equals(userId)){
            return Result.ok(
                    UserData.builder().name("hai").age(26).address("suqian").build());
        }
        if ("1003".equals(userId)){
            return Result.ok(
                    UserData.builder().name("xiao").age(26).address("shuyang").build());
        }
        return Result.fail("not found");
    }

}
