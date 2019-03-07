package com.yizhuoyang.classroomfeatures.service;

import com.yizhuoyang.classroomfeatures.controller.ClassroomController;
import com.yizhuoyang.classroomfeatures.dao.ClassScheduleDao;
import com.yizhuoyang.classroomfeatures.domain.RespRoom;
import com.yizhuoyang.classroomfeatures.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassScheduleService {

    private static Logger logger = LoggerFactory.getLogger(ClassroomController.class);

    @Resource
    private ClassScheduleDao classScheduleDao;


    public List<RespRoom> getClassScheduleByIdAndDate(Integer id, int date) {
        if (date == 0) {
            date = Utils.getDate();
        }
        try {
            List<Integer> times = classScheduleDao.getClassScheduleByIdAndDate(id, date);
            int[] res = new int[6];
            for (Integer time : times) {
                res[time] = 1;
            }
            List<RespRoom> respRooms = new ArrayList<>();
            for (int i = 1; i < 6; i++) {
                if (res[i] == 1) {
                    respRooms.add(new RespRoom(i, "上课"));
                }
            }
            return respRooms;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
