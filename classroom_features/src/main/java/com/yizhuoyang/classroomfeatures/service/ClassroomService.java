package com.yizhuoyang.classroomfeatures.service;

import com.alibaba.fastjson.JSONObject;
import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.controller.ClassroomController;
import com.yizhuoyang.classroomfeatures.dao.ClassroomDao;
import com.yizhuoyang.classroomfeatures.domain.Classroom;
import com.yizhuoyang.classroomfeatures.domain.RespRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
            return new Result(-1, "fail");
        }
    }


    public Result getRoomByCondition(String teachingBuilding, Integer size, Integer roomNumber) {
        if (teachingBuilding == null && size == null && roomNumber == null) {
            return getClassroomDetails();
        }
        StringBuilder sql = new StringBuilder("select * from classroom");
        boolean target = false;
        sql.append(" where");
        if (teachingBuilding != null) {
            sql.append(" teaching_building=\"").append(teachingBuilding).append("\"");
            target = true;
        }
        if (size != null) {
            if (target) {
                sql.append(" and");
            }
            if (size == 1) {
                sql.append(" seats_number>=120");
            } else {
                sql.append(" seats_number<120");
            }
            target = true;
        }
        if (roomNumber != null) {
            if (target) {
                sql.append(" and");
            }
            sql.append(" room_number=").append(roomNumber);
        }
        try {
            List<Classroom> list = classroomDao.selectBySQL(sql);
            return new Result(1, "success", JSONObject.toJSONString(list));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(-1, "fail");
        }
    }

    public Result getRoomDetailByIdAndDate(Integer id, Integer date) {
        try {
            Classroom classroom;
            try {
                classroom = classroomDao.getRoomDetailById(id);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return new Result(-1, "fail");
            }
            List<RespRoom> classScheduleData = classScheduleService.getClassScheduleByIdAndDate(id, date);
            List<RespRoom> reservationData = reservationService.getReservationByRoomIdAndDate(id, date);
            if (classScheduleData == null) {
                return new Result(-1, "fail");
            }
            if (reservationData != null) {
                classScheduleData.addAll(reservationData);
            }
            JSONObject object = new JSONObject();
            object.put("classroom", JSONObject.toJSON(classroom));
            object.put("status", classScheduleData);
            return new Result(1, "success", object);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(-1, "fail");
        }


    }
}
