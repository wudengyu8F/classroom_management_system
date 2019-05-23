package com.yizhuoyang.classroomfeatures.domain;

/**
 * 教室详情显示表单
 */
public class RespRoom {

    private int timeId;

    private String time;

    private String status;

    public RespRoom() {
    }

    public RespRoom(int timeId, String time, String status) {
        this.timeId = timeId;
        this.time = time;
        this.status = status;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
