package com.yizhuoyang.classroomfeatures.domain;

/**
 * 预约信息
 */
public class Appointment {

    /**
     * id
     */
    private int id;
    /**
     * 教室id
     */
    private int classroomId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 创建时间
     */
    private String createMinute;
    /**
     * 预约时间
     */
    private String reserveMinute;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateMinute() {
        return createMinute;
    }

    public void setCreateMinute(String createMinute) {
        this.createMinute = createMinute;
    }

    public String getReserveMinute() {
        return reserveMinute;
    }

    public void setReserveMinute(String reserveMinute) {
        this.reserveMinute = reserveMinute;
    }
}
