package com.yizhuoyang.classroomfeatures.service;

import com.yizhuoyang.classroomfeatures.controller.ClassroomController;
import com.yizhuoyang.classroomfeatures.dao.ReservationDao;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
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
public class ReservationService {

    private static Logger logger = LoggerFactory.getLogger(ClassroomController.class);

    @Resource
    private ReservationDao reservationDao;

    public List<RespRoom> getReservationByRoomIdAndDate(Integer id, int date) {
        if (date == 0) {
            date = Utils.getDate();
        }
        try {
            List<ReservationInfo> info = reservationDao.getReservationByRoomIdAndDate(id, date);
            int[] res = new int[6];
            for (ReservationInfo r : info) {
                if (r.isPass()) {
                    res[r.getTime()] = 2;
                } else {
                    res[r.getTime()] = 1;
                }
            }
            List<RespRoom> respRooms = new ArrayList<>();
            for (int i = 1; i < 6; i++) {
                if (res[i] == 1) {
                    respRooms.add(new RespRoom(i, "排队"));
                }
                if (res[i] == 2) {
                    respRooms.add(new RespRoom(i, "占用"));
                }
            }
            return respRooms;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }

    }
}
