package com.example.demo.activity.dao;

import com.example.demo.activity.domain.Activity;

import java.util.List;

/**
 * Created by zhong on 2018/9/2.
 */
public interface ActivityDao {

    Activity getActivityById(long activityId);

    List<Activity> getActivities();

    int insertActivity(Activity newActivity);

    long insertActivityBackGeneratedId(Activity newActivity);

    int updateActivity(Activity activity);

}
