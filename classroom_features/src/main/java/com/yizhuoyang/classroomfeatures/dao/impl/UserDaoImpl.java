package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.AbstractDao;
import com.yizhuoyang.classroomfeatures.dao.UserDao;
import com.yizhuoyang.classroomfeatures.domain.UserRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public String getPasswordByUserId(String userId) {
        String sql = "select password from user where user_id = ?";
        BeanPropertyRowMapper<UserRequest> rowMapper = new BeanPropertyRowMapper<>(UserRequest.class);
        try {
            return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, rowMapper, userId)).getPassword();
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @Override
    public int register(UserRequest userRequest) {
        String sql = "insert into user (user_id,username,password,sex,roles) values(?,?,?,?,?)";
        Md5Hash md5Hash = new Md5Hash(userRequest.getPassword());
        Object[] args = {userRequest.getUserId(), userRequest.getUsername(), md5Hash.toString(), userRequest.isSex(), userRequest.getRoles()};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public Set<String> getRolesByUserId(String userId) {
        String sql = "select roles from user where user_id=?";
        BeanPropertyRowMapper<UserRequest> rowMapper = new BeanPropertyRowMapper<>(UserRequest.class);
        UserRequest userRequest = jdbcTemplate.queryForObject(sql, rowMapper, userId);
        String[] split = Objects.requireNonNull(userRequest).getRoles().split(",");
        return new HashSet<>(Arrays.asList(split));
    }

    @Override
    public String getUserNameByUId(String uid) throws Exception {
        String sql = "select username from user where user_id=?";
        BeanPropertyRowMapper<UserRequest> rowMapper = new BeanPropertyRowMapper<>(UserRequest.class);
        return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, rowMapper, uid)).getUsername();
    }
}
