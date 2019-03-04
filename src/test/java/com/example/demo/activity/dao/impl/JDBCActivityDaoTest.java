package com.example.demo.activity.dao.impl;

import com.example.demo.activity.domain.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JDBCActivityDaoTest {

    @Autowired
    JDBCActivityDao jdbcActivityDao;

    @Test
    public void getActivityById() {
        System.out.println(jdbcActivityDao.getActivityById(1));
    }

    @Test
    public void getActivities() {
        for (Activity a : jdbcActivityDao.getActivities()){
            System.out.println(a);
        }
    }

    @Test
    public void insertActivity() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Activity activity = new Activity();
        activity.setActivityName("测试新增");
        activity.setActivityDesc("测试新增活动描述");
        activity.setStartTime(format.parse("2018-9-3 00:00:00"));
        activity.setEndTime(format.parse("2018-9-25 00:00:00"));
        int count = jdbcActivityDao.insertActivity(activity);
        assertEquals(1, count);
    }

    @Test
    public void updateActivity() {
    }

    @Test
    public void init() {
    }

    @Test
    public void insertActivityBackGeneratedId() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Activity activity = new Activity();
        activity.setActivityName("测试新增");
        activity.setActivityDesc("测试新增活动描述");
        activity.setStartTime(format.parse("2018-9-3 00:00:00"));
        activity.setEndTime(format.parse("2018-9-25 00:00:00"));
        int activityId = jdbcActivityDao.insertActivity(activity);
        System.out.println("activityId = " + activityId);
    }
}