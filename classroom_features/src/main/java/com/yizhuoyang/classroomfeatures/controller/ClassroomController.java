package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;

import com.yizhuoyang.classroomfeatures.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/cls")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @RequestMapping(value = "getTeachingBuilding")
    @ResponseBody
    public Result getTeachingBuilding(){
        return classroomService.getTeachingBuilding();
    }

    @RequestMapping(value = "getRoomList")
    @ResponseBody
    public Result getClassroomDetails() {
        return classroomService.getClassroomDetails();
    }



}
