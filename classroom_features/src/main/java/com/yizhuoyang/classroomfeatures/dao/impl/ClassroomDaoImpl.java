package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.AbstractDao;
import com.yizhuoyang.classroomfeatures.dao.ClassroomDao;
import com.yizhuoyang.classroomfeatures.domain.Classroom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ClassroomDaoImpl extends AbstractDao implements ClassroomDao {

    @Override
    public List<Classroom> getClassroomDetails() {
        String sql = "select * from classroom";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Classroom classroom = new Classroom();
            classroom.setId(rs.getInt("id"));
            classroom.setRoomNumber(rs.getInt("room_number"));
            classroom.setTeachingBuilding(rs.getString("teaching_building"));
            classroom.setSeatsNumber(rs.getInt("seats_number"));
            classroom.setRoomLocal(rs.getString("room_local"));
            return classroom;
        });
    }

    @Override
    public Classroom getRoomDetailById(Integer id) {
        String sql = "select id,teaching_building,room_local,room_number,seats_number,multimedia_equipment from classroom where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Classroom classroom = new Classroom();
            classroom.setId(rs.getInt("id"));
            classroom.setRoomLocal(rs.getString("room_local"));
            classroom.setTeachingBuilding(rs.getString("teaching_building"));
            classroom.setRoomNumber(rs.getInt("room_number"));
            classroom.setSeatsNumber(rs.getInt("seats_number"));
            classroom.setMultimediaEquipment(rs.getString("multimedia_equipment"));
            return classroom;
        });
    }

    @Override
    public List<Classroom> selectBySQL(StringBuilder sql) {
        return jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            Classroom classroom = new Classroom();
            classroom.setSeatsNumber(rs.getInt("seats_number"));
            classroom.setId(rs.getInt("id"));
            classroom.setTeachingBuilding(rs.getString("teaching_building"));
            classroom.setRoomNumber(rs.getInt("room_number"));
            classroom.setRoomLocal(rs.getString("room_local"));
            return classroom;
        });
    }

}
