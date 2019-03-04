package com.example.demo.activity.dao;

import com.example.demo.activity.domain.Privilege;

import java.util.List;

public interface PrivilegeDao {

    Privilege getPrivilegeById(long privilegeId);

    List<Privilege> getPrivileges(long activityId);

    int insertPrivilege(Privilege privilege);

    int updatePrivilege(Privilege privilege);

    int deletePrivilegeStock(int number, long privilegeId);

}
