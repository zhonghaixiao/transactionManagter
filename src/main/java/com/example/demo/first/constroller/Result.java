package com.example.demo.first.constroller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {

    private String code;
    private String message;
    T data;

    static <T> Result ok(String message, T data){
        return  Result.builder()
                .code("1")
                .message(message)
                .data(data)
                .build();
    }

    static <T> Result ok(T data){
        return  ok("success", data);
    }

    static <T> Result fail(String message, T data){
        return  Result.builder()
                .code("1")
                .message(message)
                .data(data)
                .build();
    }

    static <T> Result fail(T data){
        return  fail("fail", data);
    }

}
















