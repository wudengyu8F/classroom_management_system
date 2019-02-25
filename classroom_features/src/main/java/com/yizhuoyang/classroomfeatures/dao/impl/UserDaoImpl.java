package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.UserDao;
import com.yizhuoyang.classroomfeatures.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
public class UserDaoImpl implements UserDao {

    @Resource
    @Qualifier("mysql")
    private JdbcTemplate jdbcTemplate;


    @Override
    public String getPasswordByUsername(String username) {
        String sql = "select password from user where username = ?";
        BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, rowMapper, username)).getPassword();
        } catch (EmptyResultDataAccessException e) {
            return "";
        }
    }

    @Override
    public List<String> getRolesByUsername(String userName) {
        String sql = "select * from role where username = ?";

//        jdbcTemplate.queryForList(sql, )
        return null;
    }

    @Override
    public List<String> getPermissionsByUsername(String userName) {


        return null;
    }
}
