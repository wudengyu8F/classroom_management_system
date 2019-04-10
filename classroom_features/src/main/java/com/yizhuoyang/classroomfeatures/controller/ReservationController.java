package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
import com.yizhuoyang.classroomfeatures.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rsv")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    /**
     * 预约页面申请预约
     */
    @PostMapping(value = "/insertInfo")
    public Result insertInfo(@RequestBody(required = false) ReservationInfo reservationInfo) {
        if (reservationInfo == null) {
            return new Result(-1, "参数错误");
        }
        return reservationService.insertInfo(reservationInfo);
    }


    /**
     * 学生预约界面
     */
    @GetMapping(value = "/getStudentRSVById")
    public Result getStudentRSVById(@RequestParam(value = "uid", required = false) Integer uid) {
        if (uid == null) {
            return new Result(-1, "参数错误");
        }
        return reservationService.getStudentRSVById(uid);
    }

    /**
     * 学生预约界面取消申请操作
     */
    @GetMapping(value = "/cancelApplication")
    public Result cancelApplication(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            return new Result(-1, "参数错误");
        }
        return reservationService.cancelApplication(id);
    }

    /**
     * 审批界面显示
     */
    @GetMapping(value = "high/getApprovalDetail")
    public Result getApprovalDetail(@RequestParam(value = "date", defaultValue = "0") Integer date) {
        return reservationService.getApprovalDetail(date);
    }

    /**
     * desc: 审批界面操作
     */
    @GetMapping(value = "high/approvalOperation")
    public Result approvalOperation(@RequestParam(value = "id", required = false) Integer id,
                                    @RequestParam(value = "ope", required = false) Integer ope,
                                    @RequestParam(value = "desc", defaultValue = "") String desc) {
        if (id == null || ope == null) {
            return new Result(-1, "参数错误");
        }
        return reservationService.approvalOperation(id, ope, desc);
    }


}
