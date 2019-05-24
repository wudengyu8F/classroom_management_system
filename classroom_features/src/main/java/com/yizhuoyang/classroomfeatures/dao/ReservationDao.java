package com.yizhuoyang.classroomfeatures.dao;

import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;

import java.util.List;

public interface ReservationDao {

    List<ReservationInfo> getReservationByRoomIdAndDate(Integer id, int date) throws Exception;

    boolean insertInfo(ReservationInfo reservationInfo) throws Exception;

    List<ReservationInfo> getStudentRSVById(Integer uid, Integer date) throws Exception;

    int cancelApplication(Integer id) throws Exception;

    List<ReservationInfo> getApprovalDetail(Integer date) throws Exception;

    int approvalOperation(Integer id, Integer ope, String desc) throws Exception;
}
