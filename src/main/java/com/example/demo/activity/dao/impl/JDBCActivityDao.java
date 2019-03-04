package com.example.demo.activity.dao.impl;

import com.example.demo.activity.dao.ActivityDao;
import com.example.demo.activity.domain.Activity;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by zhong on 2018/9/2.
 */
@Repository
public class JDBCActivityDao implements ActivityDao{

    private JdbcTemplate jdbcTemplate;
    final String INSERT_SQL = "insert into activity(activity_name, activity_desc, start_time, end_time) values (?,?,?,?)";
    final String SELECT_SQL = "select * from activity";

    @Autowired
    public void init(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 根据活动ID查询活动
     * @param activityId
     * @return
     */
    @Override
    public Activity getActivityById(long activityId) {
        return jdbcTemplate.queryForObject("select * from activity where activity_id = ?", new ActivityRowMapper(), activityId);
    }

    /**
     * 查询所有的活动列表
     * @return
     */
    @Override
    public List<Activity> getActivities() {
        return jdbcTemplate.query(SELECT_SQL, new ActivityRowMapper());
    }

    /**
     * 新增一个活动
     * @param newActivity
     * @return
     */
    @Override
    public int insertActivity(Activity newActivity) {
        return jdbcTemplate.update(INSERT_SQL, newActivity.getActivityName(), newActivity.getActivityDesc(), newActivity.getStartTime(), newActivity.getEndTime());
    }

    /**
     * 获取auto-generated key
     * @param newActivity
     * @return 获取到的key
     */
    public long insertActivityBackGeneratedId(final Activity newActivity){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[]{"activity_id"});
            ps.setString(1, newActivity.getActivityName());
            ps.setString(2, newActivity.getActivityDesc());
            ps.setString(3, newActivity.getStartTime().toString());
            ps.setString(4, newActivity.getEndTime().toString());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * 修改已存在的活动
     * @param activity
     * @return
     */
    @Override
    public int updateActivity(Activity activity) {
        return 0;
    }

    class ActivityRowMapper implements RowMapper<Activity>{

        @Override
        public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
            Activity activity = new Activity();
            activity.setActivityId(rs.getInt("activity_id"));
            activity.setActivityName(rs.getString("activity_name"));
            activity.setActivityDesc(rs.getString("activity_desc"));
            activity.setStartTime(rs.getTimestamp("start_time"));
            activity.setEndTime(rs.getTimestamp("end_time"));
            activity.setCreateTime(rs.getTimestamp("create_time"));
            activity.setUpdateTime(rs.getTimestamp("update_time"));
            return activity;
        }
    }


}
