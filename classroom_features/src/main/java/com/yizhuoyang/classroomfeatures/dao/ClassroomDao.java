package com.yizhuoyang.classroomfeatures.dao;

import com.yizhuoyang.classroomfeatures.domain.Classroom;

import java.util.List;

public interface ClassroomDao {

    List<Classroom> getClassroomDetails() throws Exception;

    Classroom getRoomDetailById(Integer id) throws Exception;

    List<String> getTeachingBuilding() throws Exception;

    List<Classroom> getRoomByIdAndSize(String tId, Integer size) throws Exception;

}
