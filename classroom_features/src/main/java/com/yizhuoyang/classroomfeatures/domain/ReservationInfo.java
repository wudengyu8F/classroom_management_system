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
     * 教学楼
     */
    private String teachingBuilding;

    /**
     * 教室编号
     */
    private int roomNumber;

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
    private int isPass;

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

    public String getTeachingBuilding() {
        return teachingBuilding;
    }

    public void setTeachingBuilding(String teachingBuilding) {
        this.teachingBuilding = teachingBuilding;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    @Override
    public String toString() {
        return "ReservationInfo{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", teachingBuilding='" + teachingBuilding + '\'' +
                ", roomNumber=" + roomNumber +
                ", date=" + date +
                ", time=" + time +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", reservationDesc='" + reservationDesc + '\'' +
                ", isPass=" + isPass +
                '}';
    }
}
