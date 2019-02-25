package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoImplTest {

    @Autowired
    @Qualifier("mysql")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
//        String sql = "select password from user where username = ?";
        String sql = "select count(*) from user";
        Integer s = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(s);
    }

    @Test
    public void test2() {
        String zhangsan = userDao.getPasswordByUsername("wangwu");
        System.out.println(zhangsan+"---------------------------------------");
    }
}