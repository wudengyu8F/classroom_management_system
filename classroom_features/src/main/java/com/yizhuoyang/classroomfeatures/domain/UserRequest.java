package com.yizhuoyang.classroomfeatures.domain;

public class UserRequest extends AbstractUser {

    private boolean rememberMe;

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
