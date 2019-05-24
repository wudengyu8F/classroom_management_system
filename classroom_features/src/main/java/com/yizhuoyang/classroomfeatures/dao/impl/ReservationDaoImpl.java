package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.AbstractDao;
import com.yizhuoyang.classroomfeatures.dao.ReservationDao;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationDaoImpl extends AbstractDao implements ReservationDao {

    @Override
    public List<ReservationInfo> getReservationByRoomIdAndDate(Integer id, int date) {
        String sql = "select time,is_pass from reservation_info where room_id=? and date=? and is_pass<2";
        return jdbcTemplate.query(sql, new Object[]{id, date}, (rs, i) -> {
            ReservationInfo reservationInfo = new ReservationInfo();
            reservationInfo.setTimeId(rs.getInt("time"));
            reservationInfo.setIsPassId(rs.getInt("is_pass"));
            return reservationInfo;
        });
    }

    @Override
    public boolean insertInfo(ReservationInfo reservationInfo) {
        String sql = "insert into reservation_info (room_id, date, time, user_id, username,reservation_desc)  value (?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, reservationInfo.getRoomId(), reservationInfo.getDate(), reservationInfo.getTimeId(), reservationInfo.getUserId(), reservationInfo.getUserName(),
                reservationInfo.getReservationDesc());
        return update == 1;
    }

    @Override
    public List<ReservationInfo> getStudentRSVById(Integer uid, Integer date) {
        String sql = "select r.id as id,c.teaching_building as teaching_building,c.room_number as room_number, " +
                "r.date as date,r.time as time,r.is_pass as is_pass " +
                "from reservation_info r,classroom c " +
                "where r.user_id = ? and r.date >= ? and r.room_id=c.id and r.is_pass<>2 " +
                "order by r.time desc;";
        return jdbcTemplate.query(sql, new Object[]{uid, date}, (rs, i) -> {
            ReservationInfo reservationInfo = new ReservationInfo();
            reservationInfo.setId(rs.getInt("id"));
            reservationInfo.setTeachingBuilding(rs.getString("teaching_building"));
            reservationInfo.setIsPassId(rs.getInt("is_pass"));
            reservationInfo.setTimeId(rs.getInt("time"));
            reservationInfo.setDate(rs.getInt("date"));
            reservationInfo.setRoomNumber(rs.getInt("room_number"));
            return reservationInfo;
        });
    }

    @Override
    public int cancelApplication(Integer id) {
        String sql = "delete from reservation_info where id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<ReservationInfo> getApprovalDetail(Integer date) {
        String sql = "select r.id as id,c.teaching_building as teaching_building,c.room_number as room_number," +
                "r.date as date,r.time as time,r.username as username,r.reservation_desc as reservation_desc " +
                "from reservation_info r,classroom c " +
                "where r.date>=? and r.room_id=c.id and r.is_pass=0;";
        return jdbcTemplate.query(sql, new Object[]{date}, (rs, i) -> {
            ReservationInfo reservationInfo = new ReservationInfo();
            reservationInfo.setId(rs.getInt("id"));
            reservationInfo.setRoomNumber(rs.getInt("room_number"));
            reservationInfo.setDate(rs.getInt("date"));
            reservationInfo.setTeachingBuilding(rs.getString("teaching_building"));
            reservationInfo.setTimeId(rs.getInt("time"));
            reservationInfo.setUserName(rs.getString("username"));
            reservationInfo.setReservationDesc(rs.getString("reservation_desc"));
            return reservationInfo;
        });
    }

    @Override
    public int approvalOperation(Integer id, Integer ope, String desc) {
        String sql;
        if ("".equals(desc)) {
            sql = "update reservation_info set is_pass=? where id=?";
            return jdbcTemplate.update(sql, ope, id);
        } else {
            sql = "update reservation_info set is_pass=?,reject_desc=? where id=?";
            return jdbcTemplate.update(sql, ope, desc, id);
        }
    }
}
