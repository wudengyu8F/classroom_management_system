package com.yizhuoyang.classroomfeatures.domain;

/**
 * 教室详情显示表单
 */
public class RespRoom {

    private int time;

    private String status;

    public RespRoom() {
    }

    public RespRoom(int time, String status) {
        this.time = time;
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
