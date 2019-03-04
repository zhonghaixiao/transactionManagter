package com.example.demo.first.constroller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {

    private String name;
    private int age;
    private String address;

}
