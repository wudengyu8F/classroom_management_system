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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/subLogin")
    public Result subLogin(@RequestBody(required = false) UserRequest userRequest) {
        if (userRequest == null) {
            return new Result(-1, "参数错误");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(Integer.toString(userRequest.getUserId()), userRequest.getPassword());
        try {
            token.setRememberMe(userRequest.isRememberMe());
            subject.login(token);
            String username = userService.getUserNameByUId(token.getUsername());
            return new Result(1, "success", username);
        } catch (UnknownAccountException e) {
            //登录失败，用户名不存在
            return new Result(-1, "用户名不存在");
        } catch (IncorrectCredentialsException e) {
            //登录失败：密码错误
            return new Result(-1, "密码错误");
        } catch (Exception e){
            return new Result(-1, "服务器错误");
        }
    }

    @GetMapping(value = "/subLogin")
    public Result subLogin() {
        return new Result(0, "未登录");
    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody(required = false) UserRequest userRequest) {
        if (userRequest == null) {
            return new Result(-1, "参数错误");
        }
        return userService.register(userRequest);
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
