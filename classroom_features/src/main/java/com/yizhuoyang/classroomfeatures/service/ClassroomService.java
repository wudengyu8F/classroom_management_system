package com.yizhuoyang.classroomfeatures.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.controller.ClassroomController;
import com.yizhuoyang.classroomfeatures.dao.ClassroomDao;
import com.yizhuoyang.classroomfeatures.domain.ClassSchedule;
import com.yizhuoyang.classroomfeatures.domain.Classroom;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
import com.yizhuoyang.classroomfeatures.domain.RespRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

@Service
public class ClassroomService {

    private static Logger logger = LoggerFactory.getLogger(ClassroomController.class);

    @Resource
    private ClassroomDao classroomDao;

    private final ReservationService reservationService;

    private final ClassScheduleService classScheduleService;

    @Autowired
    public ClassroomService(ReservationService reservationService, ClassScheduleService classScheduleService) {
        this.reservationService = reservationService;
        this.classScheduleService = classScheduleService;
    }


    public Result getClassroomDetails() {
        try {
            List<Classroom> list = classroomDao.getClassroomDetails();
            return new Result(1, "success", JSONObject.toJSONString(list));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(0, "fail");
        }
    }


    public Result getRoomByIdAndSize(String teachingBuilding, Integer size) {
        if ("0".equals(teachingBuilding) && size == 0) {
            return getClassroomDetails();
        }
        try {
            List<Classroom> list = classroomDao.getRoomByIdAndSize(teachingBuilding, size);
            return new Result(1, "success", JSONObject.toJSONString(list));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(0, "fail");
        }
    }

    public Result getTeachingBuilding() {
        try {
            List<String> list = classroomDao.getTeachingBuilding();
            HashSet<String> strings = new HashSet<>(list);
            return new Result(1, "success", JSONObject.toJSONString(strings));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(0, "fail");
        }
    }


    public Result getRoomDetailByIdAndDate(Integer id, Integer date) {
        try {
            Classroom classroom = classroomDao.getRoomDetailById(id);
            List<RespRoom> classScheduleData = classScheduleService.getClassScheduleByIdAndDate(id, date);
            List<RespRoom> reservationData = reservationService.getReservationByRoomIdAndDate(id, date);
            if (classScheduleData == null) {
                return new Result(0, "fail");
            }
            if (reservationData != null) {
                classScheduleData.addAll(reservationData);
            }
            JSONObject object = new JSONObject();
            object.put("classroom", JSONObject.toJSON(classroom));
            object.put("status", classScheduleData);
            return new Result(1, "success", object);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return new Result(0, "fail");
        }


    }
}
