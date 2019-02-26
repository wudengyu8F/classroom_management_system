package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.domain.UserRequest;
import com.yizhuoyang.classroomfeatures.service.UserService;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/login")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/subLogin", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result subLogin(UserRequest userRequest) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(Integer.toString(userRequest.getUserId()), userRequest.getPassword());
        try {
            token.setRememberMe(userRequest.isRememberMe());
            subject.login(token);
            return new Result(1, "success");
        } catch (UnknownAccountException e) {
            //登录失败，用户名不存在
            return new Result(0, "用户名不存在");
        } catch (IncorrectCredentialsException e) {
            //登录失败：密码错误
            return new Result(-1, "密码错误");
        }
    }

    @PostMapping(value = "/register", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result register(UserRequest userRequest) {
        return userService.register(userRequest);
    }

    @RequestMapping("/add")
    public String getTestPage() {
        return "index";
    }

    @PostMapping(value = "/error", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result error() {
        return new Result(0, "未授权");
    }


}
