package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/login")
public class JumpController {

    @GetMapping(value = "/subLogin")
    public Result subLogin() {
        return new Result(0, "未登录");
    }

    @GetMapping(value = "/error")
    public Result error() {
        return new Result(4, "权限不够");
    }

    @GetMapping(value = "/out")
    public Result logout() {
        return new Result(1, "正常退出");
    }
}
