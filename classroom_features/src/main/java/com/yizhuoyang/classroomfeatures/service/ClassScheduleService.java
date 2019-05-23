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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassScheduleService {

    private static Logger logger = LoggerFactory.getLogger(ClassroomController.class);

    @Resource
    private ClassScheduleDao classScheduleDao;


    public List<RespRoom> getClassScheduleByIdAndDate(Integer id, int date) {
        if (date == 0) {
            date = Utils.getDate();
        }
        Map<Integer, String> map = new HashMap<Integer, String>(16) {
            {
                put(1, "1,2节");
                put(2, "3,4节");
                put(3, "5,6节");
                put(4, "7,8节");
                put(5, "9,10节");
            }
        };
        try {
            List<Integer> times = classScheduleDao.getClassScheduleByIdAndDate(id, date);
            int[] res = new int[6];
            for (Integer time : times) {
                res[time] = 1;
            }
            List<RespRoom> respRooms = new ArrayList<>();
            for (int i = 1; i < 6; i++) {
                if (res[i] == 1) {
                    respRooms.add(new RespRoom(i, map.get(i), "上课"));
                }
            }
            return respRooms;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
