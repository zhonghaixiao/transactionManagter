package com.example.demo.activity.service;

import com.example.demo.activity.dao.impl.JDBCActivityDao;
import com.example.demo.activity.dao.impl.JdbcPrivilegeDao;
import com.example.demo.activity.domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private JDBCActivityDao activityDao;

    private JdbcPrivilegeDao privilegeDao;

    @Autowired
    public void setActivityDao(JDBCActivityDao activityDao){
        this.activityDao = activityDao;
    }

    @Autowired
    public void setPrivilegeDao(JdbcPrivilegeDao privilegeDao){
        this.privilegeDao = privilegeDao;
    }

    public void createActivity(Activity activity){

    }

}
