package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.dao.UserDao;
import com.yizhuoyang.classroomfeatures.domain.UserRequest;
import com.yizhuoyang.classroomfeatures.service.UserService;
import com.yizhuoyang.classroomfeatures.component.JedisPoolFactory;
import com.yizhuoyang.classroomfeatures.util.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private JedisPoolFactory factory;

    @Autowired
    private JedisUtil jedisUtil;

    @Test
    public void test() {
//        String sql = "select password from user where username = ?";
        String sql = "select count(*) from user";
        Integer s = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(s);
    }

    @Test
    public void test2() {
        String zhangsan = userDao.getPasswordByUserId("wangwu");
        System.out.println(zhangsan + "---------------------------------------");
    }

    @Test
    public void test3() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(23453464);
        userRequest.setUsername("wangwu");
        userRequest.setPassword("123456");
        userRequest.setRole("student");
        userRequest.setSex(true);
        userRequest.setSalt("sadfuwecalsduw");
        Result register = userService.register(userRequest);
        System.out.println(register);
    }

    @Test
    public void test4() {
        JedisPool jedisPool = factory.getJedisPool();
        Jedis jedis = jedisPool.getResource();

    }
}