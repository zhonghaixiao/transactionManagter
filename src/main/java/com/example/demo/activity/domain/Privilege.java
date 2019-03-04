package com.example.demo.activity.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Privilege {
    private long privilegeId;
    private String privilegeName;
    private String privilegeDesc;
    private int stockNumber;
    private Date createTime;
    private Date updateTime;
}
