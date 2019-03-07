package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;

import com.yizhuoyang.classroomfeatures.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "getRoomList", method = RequestMethod.GET)
    @ResponseBody
    public Result getClassroomDetails() {
        return classroomService.getClassroomDetails();
    }

    /**
     * 获得教室页的教学楼信息
     */
    @RequestMapping(value = "getTeachingBuilding")
    @ResponseBody
    public Result getTeachingBuilding() {
        return classroomService.getTeachingBuilding();
    }

    /**
     * 通过条件获取教室信息
     * tid 不选给0，选教学楼给教学楼
     * size 不选给0，选了大于120座给1，小于120座给2
     */
    @RequestMapping(value = "getRoomByTIdAndSize")
    @ResponseBody
    public Result getRoomByIdAndSize(@RequestParam(name = "teachingBuilding") String teachingBuilding, @RequestParam(name = "size") Integer size) {
        return classroomService.getRoomByIdAndSize(teachingBuilding, size);
    }

    /**
     * 通过id和date获取教室的详情
     *
     * date为0则显示当前时间
     */
    @RequestMapping(value = "getRoomDetailByIdAndDate", method = RequestMethod.GET)
    @ResponseBody
    public Result getRoomDetailByIdAndDate(@RequestParam(name = "id") Integer id, @RequestParam(name = "date") Integer date) {
        return classroomService.getRoomDetailByIdAndDate(id,date);
    }



}
