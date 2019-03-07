package com.yizhuoyang.classroomfeatures.dao.impl;

import com.yizhuoyang.classroomfeatures.dao.AbstractDao;
import com.yizhuoyang.classroomfeatures.dao.ClassroomDao;
import com.yizhuoyang.classroomfeatures.domain.Classroom;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class ClassroomDaoImpl extends AbstractDao implements ClassroomDao {

    @Override
    public List<Classroom> getClassroomDetails() throws SQLException {
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
    public Classroom getRoomDetailById(Integer id) throws SQLException {
        String sql = "select id,teaching_building,room_number from classroom where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Classroom classroom = new Classroom();
            classroom.setId(rs.getInt("id"));
            classroom.setTeachingBuilding(rs.getString("teaching_building"));
            classroom.setRoomNumber(rs.getInt("room_number"));
            return classroom;
        });
    }

    @Override
    public List<String> getTeachingBuilding() {
        String sql = "select teaching_building from classroom";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @Override
    public List<Classroom> getRoomByIdAndSize(String teachingBuilding, Integer size) {
        StringBuilder sql = new StringBuilder("select * from classroom");
        if (size != 0) {
            if ("0".equals(teachingBuilding)) {
                if (size == 1) {
                    sql.append(" where seats_number>120");
                } else {
                    sql.append(" where seats_number<120");
                }
            } else {
                if (size == 1) {
                    sql.append(" where teaching_building=\"").append(teachingBuilding).append("\"").append(" and seats_number>120");
                } else {
                    sql.append(" where teaching_building=\"").append(teachingBuilding).append("\"").append(" and seats_number<120");
                }
            }
        } else {
            if (!"0".equals(teachingBuilding)) {
                sql.append(" where teaching_building=\"").append(teachingBuilding).append("\"");
            }
        }
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
