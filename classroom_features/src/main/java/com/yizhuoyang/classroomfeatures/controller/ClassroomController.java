package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;

import com.yizhuoyang.classroomfeatures.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public Result getClassroomDetails() {
        return classroomService.getClassroomDetails();
    }

    /**
     * 通过条件获取教室信息
     */
    @GetMapping(value = "/getRoomByCondition")
    public Result getRoomByIdAndSize(@RequestParam(name = "teachingBuilding", required = false) String teachingBuilding,
                                     @RequestParam(name = "size", required = false) Integer size,
                                     @RequestParam(name = "room_number", required = false) Integer roomNumber) {
        return classroomService.getRoomByCondition(teachingBuilding, size, roomNumber);
    }

    /**
     * 通过id和date获取教室的详情
     */
    @GetMapping(value = "/getRoomDetailByIdAndDate")
    public Result getRoomDetailByIdAndDate(@RequestParam(name = "id", required = false) Integer id,
                                           @RequestParam(name = "date", defaultValue = "0") Integer date) {
        if (id == null) {
            return new Result(-1, "参数错误");
        }
        return classroomService.getRoomDetailByIdAndDate(id, date);
    }

}
