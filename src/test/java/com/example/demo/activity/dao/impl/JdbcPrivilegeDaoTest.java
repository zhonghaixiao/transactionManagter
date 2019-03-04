package com.example.demo.activity.dao.impl;

import com.example.demo.activity.domain.Privilege;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcPrivilegeDaoTest {

    @Autowired
    JdbcPrivilegeDao jdbcPrivilegeDao;

    @Test
    public void init() {
    }

    @Test
    public void insertPrivilege() {
        Privilege privilege = new Privilege();
        privilege.setPrivilegeName("满100减20");
        privilege.setPrivilegeDesc("满100减20 description");
        privilege.setStockNumber(10);
        int count = jdbcPrivilegeDao.insertPrivilege(privilege);
        assertEquals(1, count);
    }

    @Test
    public void updatePrivilege() {

    }

    @Test
    public void deletePrivilegeStock() {
        Privilege privilege = jdbcPrivilegeDao.getPrivilegeById(1L);
        System.out.println(privilege);
        int count = jdbcPrivilegeDao.deletePrivilegeStock(5, 1L);
        privilege = jdbcPrivilegeDao.getPrivilegeById(1L);
        System.out.println(privilege);
    }

    @Test
    public void getPrivilegeById() {
    }

    @Test
    public void getPrivileges() {
    }
}