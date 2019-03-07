package com.yizhuoyang.classroomfeatures.domain;

/**
 * 教室
 */
public class Classroom {

    /**
     * id
     */
    private int id;

    /**
     * 教学楼
     */
    private String teachingBuilding;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", teachingBuilding='" + teachingBuilding + '\'' +
                ", roomNumber=" + roomNumber +
                ", roomLocal='" + roomLocal + '\'' +
                ", seatsNumber=" + seatsNumber +
                ", multimediaEquipment='" + multimediaEquipment + '\'' +
                '}';
    }
}
