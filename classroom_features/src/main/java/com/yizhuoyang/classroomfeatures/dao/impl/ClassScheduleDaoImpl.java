package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.AbstractDao;
import com.yizhuoyang.classroomfeatures.dao.ClassScheduleDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassScheduleDaoImpl extends AbstractDao implements ClassScheduleDao {

    @Override
    public List<Integer> getClassScheduleByIdAndDate(Integer id, int date) throws Exception {
        String sql = "select time from class_schedule where room_id=? and date=?";
        return jdbcTemplate.queryForList(sql, new Object[]{id, date}, Integer.class);
    }
}
