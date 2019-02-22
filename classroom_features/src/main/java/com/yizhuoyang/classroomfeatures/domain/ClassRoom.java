package com.yizhuoyang.classroomfeatures.domain;

/**
 * 教室
 */
public class ClassRoom {

    /**
     * id
     */
    private int id;
    /**
     * 教室编号
     */
    private int roomNumber;
    /**
     * 教室位置
     */
    private String roomLocal;
    /**
     * 教室座位数量
     */
    private int seatsNumber;
    /**
     * 教室器材信息
     */
    private String multimediaEquipment;
    /**
     * 教室是否被预约
     */
    private int isAppointment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomLocal() {
        return roomLocal;
    }

    public void setRoomLocal(String roomLocal) {
        this.roomLocal = roomLocal;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getMultimediaEquipment() {
        return multimediaEquipment;
    }

    public void setMultimediaEquipment(String multimediaEquipment) {
        this.multimediaEquipment = multimediaEquipment;
    }

    public int getIsAppointment() {
        return isAppointment;
    }

    public void setIsAppointment(int isAppointment) {
        this.isAppointment = isAppointment;
    }
}
