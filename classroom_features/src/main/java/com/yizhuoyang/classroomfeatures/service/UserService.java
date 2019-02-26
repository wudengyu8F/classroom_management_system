package com.yizhuoyang.classroomfeatures.service;

import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.dao.UserDao;
import com.yizhuoyang.classroomfeatures.domain.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Result register(UserRequest userRequest) {
        try {
            int code = userDao.register(userRequest);
            return new Result(code, "success");
        } catch (Exception e) {
            return new Result(0, e.getMessage());
        }
    }
}
