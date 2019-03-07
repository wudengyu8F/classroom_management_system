package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
import com.yizhuoyang.classroomfeatures.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("rsv")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    /**
     * {
     * 	"roomId": 1,
     * 	"date": 20190306,
     * 	"time": 3,
     * 	"userId": 10000111,
     * 	"userName": "zhangsan",
     * 	"reservationDesc": "计算机协会又来了"
     * }
     *
     * Content-Type:application/json; charset=utf-8
     * @param reservationInfo
     * @return
     */
    @RequestMapping(value = "insertInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result insertInfo(@RequestBody ReservationInfo reservationInfo) {
        return reservationService.insertInfo(reservationInfo);
    }

}
