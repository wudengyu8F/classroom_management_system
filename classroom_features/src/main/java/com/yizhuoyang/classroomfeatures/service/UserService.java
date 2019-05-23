package com.yizhuoyang.classroomfeatures.service;

import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.controller.ClassroomController;
import com.yizhuoyang.classroomfeatures.dao.UserDao;
import com.yizhuoyang.classroomfeatures.domain.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(ClassroomController.class);

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Result register(UserRequest userRequest) {
        try {
            int code = userDao.register(userRequest);
            if (code == 1) {
                return new Result(1, "成功");
            } else {
                return new Result(-1, "失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(-1, "失败");
        }
    }

    public String getUserNameByUId(String uid) throws Exception {
        return userDao.getUserNameByUId(uid);
    }
}
