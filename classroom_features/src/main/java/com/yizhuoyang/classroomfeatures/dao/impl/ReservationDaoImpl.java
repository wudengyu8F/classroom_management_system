package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.AbstractDao;
import com.yizhuoyang.classroomfeatures.dao.ReservationDao;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationDaoImpl extends AbstractDao implements ReservationDao {

    @Override
    public List<ReservationInfo> getReservationByRoomIdAndDate(Integer id, int date) throws Exception {
        String sql = "select time,is_pass from reservation_info where room_id=? and date=?";
        return jdbcTemplate.query(sql, new Object[]{id, date}, (rs, i) -> {
            ReservationInfo reservationInfo = new ReservationInfo();
            reservationInfo.setTime(rs.getInt("time"));
            reservationInfo.setPass(rs.getBoolean("is_pass"));
            return reservationInfo;
        });
    }

    @Override
    public boolean insertInfo(ReservationInfo reservationInfo) throws Exception {
        String sql = "insert into reservation_info (room_id, date, time, user_id, username,reservation_desc)  value (?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, reservationInfo.getRoomId(), reservationInfo.getDate(), reservationInfo.getTime(), reservationInfo.getUserId(), reservationInfo.getUserName(),
                reservationInfo.getReservationDesc());
        return update == 1;
    }
}
