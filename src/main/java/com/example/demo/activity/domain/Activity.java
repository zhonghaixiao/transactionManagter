package com.example.demo.activity.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2018/9/2.
 */
@Data
public class Activity {
    private long activityId;
    private long privilegeId;
    private String activityName;
    private String activityDesc;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Date updateTime;
    private List<Privilege> privilegeList;
}
