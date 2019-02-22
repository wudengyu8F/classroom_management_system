package com.yizhuoyang.classroomfeatures.constant;

/**
 * Created by wudengyu8f@foxmail.com on 2019/02/15
 */
public enum UserPermission {

    /**
     * 教师
     */
    TEACHER(0, "teacher"),

    /**
     * 学生
     */
    STUDENT(1, "student"),

    /**
     * 管理员
     */
    ADMINISTRATOR(2, "administrator");

    private int code;

    private String desc;


    UserPermission(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }}
