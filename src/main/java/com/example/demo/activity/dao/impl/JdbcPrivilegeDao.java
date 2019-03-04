package com.example.demo.activity.dao.impl;

import com.example.demo.activity.dao.PrivilegeDao;
import com.example.demo.activity.domain.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcPrivilegeDao implements PrivilegeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Privilege getPrivilegeById(long privilegeId) {
        return jdbcTemplate.queryForObject("select * from privilege where privilege_id = ?", new PrivilegeRowMapper(),
                privilegeId);
    }

    @Override
    public List<Privilege> getPrivileges(long activityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int insertPrivilege(Privilege privilege) {
        return jdbcTemplate.update("insert into privilege(privilege_name, privilege_desc, stock_number)" +
                "values(?,?,?)", privilege.getPrivilegeName(), privilege.getPrivilegeDesc(), privilege.getStockNumber());
    }

    @Override
    public int updatePrivilege(Privilege privilege) {
        return jdbcTemplate.update("update privilege set privilege_name = ?, privilege_desc = ?, stock_number = ?",
                privilege.getPrivilegeName(), privilege.getPrivilegeDesc(), privilege.getStockNumber());
    }

    @Override
    public int deletePrivilegeStock(int number, long privilegeId) {
        return jdbcTemplate.update("update privilege set stock_number = ? where privilege_id = ?",
                number, privilegeId);
    }

    class PrivilegeRowMapper implements RowMapper<Privilege>{

        @Override
        public Privilege mapRow(ResultSet rs, int rowNum) throws SQLException {
            Privilege privilege = new Privilege();
            privilege.setPrivilegeId(rs.getInt("privilege_id"));
            privilege.setPrivilegeName(rs.getString("privilege_name"));
            privilege.setPrivilegeDesc(rs.getString("privilege_desc"));
            privilege.setStockNumber(rs.getInt("stock_number"));
            privilege.setCreateTime(rs.getTimestamp("create_time"));
            privilege.setUpdateTime(rs.getTimestamp("update_time"));
            return privilege;
        }
    }

}
