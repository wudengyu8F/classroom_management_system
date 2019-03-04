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
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/subLogin", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result subLogin() {
        return new Result(0, "未登录");
    }

    @PostMapping(value = "/register", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result register(UserRequest userRequest) {
        return userService.register(userRequest);
    }

    @GetMapping(value = "/add")
    public String getTestPage() {
        return "index";
    }

    @GetMapping(value = "/test")
    public String getTest() {
        return "index";
    }

    @GetMapping(value = "/error", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result error() {
        return new Result(0, "权限不够");
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Result logout() {
        return new Result(1, "正常退出");
    }

    @RequestMapping(value = "/success")
    @ResponseBody
    public Result success() {
        return new Result(1, "success");
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }
}
