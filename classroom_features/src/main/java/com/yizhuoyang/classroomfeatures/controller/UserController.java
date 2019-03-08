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
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/login")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/subLogin")
    @ResponseBody
    public Result subLogin(@RequestBody UserRequest userRequest) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(Integer.toString(userRequest.getUserId()), userRequest.getPassword());
        try {
            token.setRememberMe(userRequest.isRememberMe());
            subject.login(token);
            return new Result(1, "success");
        } catch (UnknownAccountException e) {
            //登录失败，用户名不存在
            return new Result(2, "用户名不存在");
        } catch (IncorrectCredentialsException e) {
            //登录失败：密码错误
            return new Result(3, "密码错误");
        }
    }

    @GetMapping(value = "/subLogin")
    @ResponseBody
    public Result subLogin() {
        return new Result(0, "未登录");
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public Result register(@RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    }

//    权限和角色测试
//    @GetMapping(value = "/add")
//    public String getTestPage() {
//        return "index";
//    }
//
//    @GetMapping(value = "/test")
//    public String getTest() {
//        return "index";
//    }

    @GetMapping(value = "/error")
    @ResponseBody
    public Result error() {
        return new Result(4, "权限不够");
    }

//    @GetMapping(value = "/logout")
//    @ResponseBody
//    public Result logout() {
//        return new Result(1, "正常退出");
//    }

//    @GetMapping(value = "/success")
//    @ResponseBody
//    public Result success() {
//        return new Result(1, "登录成功");
//    }

//    @GetMapping("/index")
//    public String toIndex() {
//        return "index";
//    }
}
