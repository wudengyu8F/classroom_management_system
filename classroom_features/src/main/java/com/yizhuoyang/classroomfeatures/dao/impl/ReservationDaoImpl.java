package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.AbstractDao;
import com.yizhuoyang.classroomfeatures.dao.ReservationDao;
import com.yizhuoyang.classroomfeatures.domain.Classroom;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
import com.yizhuoyang.classroomfeatures.domain.RespRoom;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class ReservationDaoImpl extends AbstractDao implements ReservationDao {

    @Override
    public List<ReservationInfo> getReservationByRoomIdAndDate(Integer id, int date) throws SQLException {
        String sql = "select time,is_pass from reservation_info where room_id=? and date=?";
        return jdbcTemplate.query(sql, new Object[]{id, date}, (rs, i) -> {
            ReservationInfo reservationInfo = new ReservationInfo();
            reservationInfo.setTime(rs.getInt("time"));
            reservationInfo.setPass(rs.getBoolean("is_pass"));
            return reservationInfo;
        });
    }
}
