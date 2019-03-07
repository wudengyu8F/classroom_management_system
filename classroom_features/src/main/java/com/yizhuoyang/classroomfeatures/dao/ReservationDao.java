package com.yizhuoyang.classroomfeatures.dao;

import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;

import java.util.List;

public interface ReservationDao {

    List<ReservationInfo> getReservationByRoomIdAndDate(Integer id, int date) throws Exception;

    boolean insertInfo(ReservationInfo reservationInfo) throws Exception;
}
