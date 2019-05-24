package com.yizhuoyang.classroomfeatures.service;

import com.alibaba.fastjson.JSONObject;
import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.controller.ClassroomController;
import com.yizhuoyang.classroomfeatures.dao.ReservationDao;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
import com.yizhuoyang.classroomfeatures.domain.RespRoom;
import com.yizhuoyang.classroomfeatures.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                if (r.getIsPassId() == 1) {
                    res[r.getTimeId()] = 2;
                } else {
                    res[r.getTimeId()] = 1;
                }
            }
            List<RespRoom> respRooms = new ArrayList<>();
            for (int i = 1; i < 6; i++) {
                if (res[i] == 1) {
                    respRooms.add(new RespRoom(i, Utils.getTimeMapping(i), "排队"));
                }
                if (res[i] == 2) {
                    respRooms.add(new RespRoom(i, Utils.getTimeMapping(i), "占用"));
                }
            }
            return respRooms;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

    }

    public Result insertInfo(ReservationInfo reservationInfo) {
        try {
            if (reservationInfo.getDate() == 0) {
                reservationInfo.setDate(Utils.getDate());
            }
            boolean flag = reservationDao.insertInfo(reservationInfo);
            if (flag) {
                return new Result(1, "成功");
            } else {
                return new Result(-1, "失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(-1, "失败");
        }
    }

    public Result getStudentRSVById(Integer uid) {
        try {
            int date = Utils.getDate();
            List<ReservationInfo> details = reservationDao.getStudentRSVById(uid, date);
            for (ReservationInfo r : details) {
                r.setTime(Utils.getTimeMapping(r.getTimeId()));
                r.setIsPass(Utils.getCodeMapping(r.getIsPassId()));
            }
            return new Result(1, "成功", JSONObject.toJSONString(details));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(-1, "失败");
        }
    }

    public Result cancelApplication(Integer id) {
        try {
            int res = reservationDao.cancelApplication(id);
            if (res == 1) {
                return new Result(1, "成功");
            } else {
                return new Result(-1, "失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(-1, "失败");
        }
    }

    public Result getApprovalDetail(Integer date) {
        if (date == 0) {
            date = Utils.getDate();
        }
        try {
            List<ReservationInfo> details = reservationDao.getApprovalDetail(date);
            for (ReservationInfo r : details) {
                r.setTime(Utils.getTimeMapping(r.getTimeId()));
            }
            return new Result(1, "成功", JSONObject.toJSONString(details));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(-1, "失败");
        }
    }

    public Result approvalOperation(Integer id, Integer ope, String desc) {
        try {
            int res = reservationDao.approvalOperation(id, ope, desc);
            if (res == 1) {
                return new Result(1, "成功");
            } else {
                return new Result(-1, "失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(-1, "失败");
        }
    }
}
