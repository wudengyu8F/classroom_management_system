package com.yizhuoyang.classroomfeatures.service;

import com.alibaba.fastjson.JSONObject;
import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.controller.ClassroomController;
import com.yizhuoyang.classroomfeatures.dao.ClassroomDao;
import com.yizhuoyang.classroomfeatures.domain.Classroom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassroomService {

    private static Logger logger = LoggerFactory.getLogger(ClassroomController.class);

    @Resource
    private ClassroomDao classroomDao;

    public Result getClassroomDetails() {
        try {
            List<Classroom> list = classroomDao.getClassroomDetails();
            return new Result(1, "success", JSONObject.toJSONString(list));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(0, "fail");
        }

    }
}
