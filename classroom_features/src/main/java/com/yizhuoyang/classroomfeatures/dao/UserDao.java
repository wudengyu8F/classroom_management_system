package com.yizhuoyang.classroomfeatures.dao;

import com.yizhuoyang.classroomfeatures.domain.UserRequest;

import java.util.List;
import java.util.Set;

public interface UserDao {

    String getPasswordByUserId(String userId);

    int register(UserRequest userRequest);

    Set<String> getPermissionsByUserId(String userId);

    List<String> getRolesByUserId(String userId);
}