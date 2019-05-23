package com.yizhuoyang.classroomfeatures.dao;

import com.yizhuoyang.classroomfeatures.domain.Classroom;

import java.util.List;
import java.util.Map;

public interface ClassroomDao {

    List<Classroom> getClassroomDetails() throws Exception;

    Classroom getRoomDetailById(Integer id) throws Exception;

    List<Classroom> selectBySQL(StringBuilder sql) throws Exception;
}
