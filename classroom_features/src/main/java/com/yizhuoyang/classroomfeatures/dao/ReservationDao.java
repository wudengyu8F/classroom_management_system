package com.yizhuoyang.classroomfeatures.dao;

import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDao {

    List<ReservationInfo> getReservationByRoomIdAndDate(Integer id, int date) throws SQLException;
}
