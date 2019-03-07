package com.yizhuoyang.classroomfeatures.domain;

/**
 * 预约信息
 */
public class ReservationInfo {

    /**
     * id
     */
    private int id;

    /**
     * 教室id
     */
    private int roomId;

    /**
     * 预约日期
     */
    private int date;

    /**
     * 预约时间
     */
    private int time;

    /**
     * 预约用户学号
     */
    private int userId;

    /**
     * 预约用户姓名
     */
    private String userName;

    /**
     * 预约理由描述
     */
    private String reservationDesc;

    /**
     * 是否通过预约
     */
    private boolean isPass;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReservationDesc() {
        return reservationDesc;
    }

    public void setReservationDesc(String reservationDesc) {
        this.reservationDesc = reservationDesc;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }

    @Override
    public String toString() {
        return "ReservationInfo{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", date=" + date +
                ", time=" + time +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", reservationDesc='" + reservationDesc + '\'' +
                ", isPass=" + isPass +
                '}';
    }
}
