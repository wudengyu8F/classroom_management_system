package com.yizhuoyang.classroomfeatures.dao;

import java.sql.SQLException;
import java.util.List;

public interface ClassScheduleDao {

    List<Integer> getClassScheduleByIdAndDate(Integer id, int date) throws SQLException;
}
