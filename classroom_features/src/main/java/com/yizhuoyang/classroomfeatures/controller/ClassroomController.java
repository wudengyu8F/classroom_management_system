package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;

import com.yizhuoyang.classroomfeatures.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/cls")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    /**
     * 获得教室页的数据
     */
    @GetMapping(value = "/getRoomList")
    @ResponseBody
    public Result getClassroomDetails() {
        return classroomService.getClassroomDetails();
    }

    /**
     * 获得教室页的教学楼信息
     */
    @GetMapping(value = "/getTeachingBuilding")
    @ResponseBody
    public Result getTeachingBuilding() {
        return classroomService.getTeachingBuilding();
    }

    /**
     * 通过条件获取教室信息
     */
    @GetMapping(value = "/getRoomByTIdAndSize")
    @ResponseBody
    public Result getRoomByIdAndSize(@RequestParam(name = "teachingBuilding") String teachingBuilding, @RequestParam(name = "size") Integer size) {
        return classroomService.getRoomByIdAndSize(teachingBuilding, size);
    }

    /**
     * 通过id和date获取教室的详情
     */
    @GetMapping(value = "/getRoomDetailByIdAndDate")
    @ResponseBody
    public Result getRoomDetailByIdAndDate(@RequestParam(name = "id") Integer id, @RequestParam(name = "date") Integer date) {
        return classroomService.getRoomDetailByIdAndDate(id, date);
    }

    /**
     * 通过id获取教室的详情
     */
    @GetMapping(value = "/getRoomDetailById")
    @ResponseBody
    public Result getRoomDetailById(@RequestParam(name = "id") Integer id) {
        return classroomService.getRoomDetailByIdAndDate(id, 0);
    }

}
