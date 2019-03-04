package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.ClassroomDao;
import com.yizhuoyang.classroomfeatures.domain.Classroom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ClassroomDaoImpl implements ClassroomDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Classroom> getClassroomDetails() throws SQLException {
        String sql = "select * from classroom";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Classroom classroom = new Classroom();
            classroom.setId(resultSet.getInt("id"));
            classroom.setRoomLocal(resultSet.getString("roomlocal"));
            return classroom;
        });
    }
}
