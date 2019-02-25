package com.yizhuoyang.classroomfeatures.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/add")
    public String getTestPage(){
        return "index";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "login";
    }
}
