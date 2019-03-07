package com.yizhuoyang.classroomfeatures.dao;

import com.yizhuoyang.classroomfeatures.domain.Classroom;

import java.sql.SQLException;
import java.util.List;

public interface ClassroomDao {

    List<Classroom> getClassroomDetails() throws SQLException;

    Classroom getRoomDetailById(Integer id) throws SQLException;

    List<String> getTeachingBuilding();

    List<Classroom> getRoomByIdAndSize(String tId, Integer size);

}
