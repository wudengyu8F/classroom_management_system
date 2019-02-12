package com.yizhuoyang.classroomfeatures.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "controller")
public class TestController {

    @RequestMapping("test")
    public String getTestPage(){
        return "index";
    }
}
