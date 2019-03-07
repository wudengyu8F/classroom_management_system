package com.yizhuoyang.classroomfeatures.dao;

import com.yizhuoyang.classroomfeatures.dao.impl.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public abstract class AbstractDao {

    protected static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Resource
    protected JdbcTemplate jdbcTemplate;
}
