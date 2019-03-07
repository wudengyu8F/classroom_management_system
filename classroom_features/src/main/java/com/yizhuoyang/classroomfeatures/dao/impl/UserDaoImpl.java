package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.AbstractDao;
import com.yizhuoyang.classroomfeatures.dao.UserDao;
import com.yizhuoyang.classroomfeatures.domain.UserRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.*;

@Component
public class UserDaoImpl extends AbstractDao implements UserDao {

//    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
//
//    @Resource
//    private JdbcTemplate jdbcTemplate;

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
        String sql = "insert into user (user_id,username,password,sex) values(?,?,?,?)";
        Md5Hash md5Hash = new Md5Hash(userRequest.getPassword());
        Object[] args = {userRequest.getUserId(), userRequest.getUsername(), md5Hash.toString(), userRequest.isSex() ? 1 : 0};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public Set<String> getPermissionsByUserId(String userId) {
        String sql = "select perms from user where user_id=?";
        BeanPropertyRowMapper<UserRequest> rowMapper = new BeanPropertyRowMapper<>(UserRequest.class);
        UserRequest userRequest = jdbcTemplate.queryForObject(sql, rowMapper, userId);
        String[] split = Objects.requireNonNull(userRequest).getPerms().split(",");
        return new HashSet<>(Arrays.asList(split));
    }

    @Override
    public Set<String> getRolesByUserId(String userId) {
        String sql = "select roles from user where user_id=?";
        BeanPropertyRowMapper<UserRequest> rowMapper = new BeanPropertyRowMapper<>(UserRequest.class);
        UserRequest userRequest = jdbcTemplate.queryForObject(sql, rowMapper, userId);
        String[] split = Objects.requireNonNull(userRequest).getRoles().split(",");
        return new HashSet<>(Arrays.asList(split));
    }
}
