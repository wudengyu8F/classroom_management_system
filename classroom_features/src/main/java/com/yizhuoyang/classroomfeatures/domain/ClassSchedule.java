package com.yizhuoyang.classroomfeatures.domain;

/**
 * 课程表
 */
public class ClassSchedule {

    /**
     * id
     */
    private int id;

    /**
     * 教室id
     */
    private int roomId;

    /**
     * 课程id
     */
    private int subjectId;

    /**
     * 日期
     */
    private int date;

    /**
     * 时间
     */
    private int time;

    /**
     * 教师id
     */
    private int teacherId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "ClassSchedule{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", subjectId=" + subjectId +
                ", date=" + date +
                ", time=" + time +
                ", teacherId=" + teacherId +
                '}';
    }
}
