package com.yizhuoyang.classroomfeatures.dao;

import java.util.List;

public interface UserDao {


    String getPasswordByUsername(String username);

    List<String> getRolesByUsername(String userName);

    List<String> getPermissionsByUsername(String userName);
}
