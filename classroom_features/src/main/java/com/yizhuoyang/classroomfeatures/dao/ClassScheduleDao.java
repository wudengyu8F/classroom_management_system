package com.yizhuoyang.classroomfeatures.dao;

import java.util.List;

public interface ClassScheduleDao {

    List<Integer> getClassScheduleByIdAndDate(Integer id, int date) throws Exception;
}
