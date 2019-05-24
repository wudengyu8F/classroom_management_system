package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@RestController
public class UtilController {

    private static Logger logger = LoggerFactory.getLogger(UtilController.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/utils")
    public Result createClassSchedule() {
        String sql = "insert into class_schedule (room_id,subject_id,date,time,teacher_id) values (?,?,?,?,?)";
        Random random = new Random();
        int executeCount = 0;
        int errorCount = 0;
        for (int k = 0; k < 7; k++) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 3; j++) {
                    executeCount++;
                    try {
                        jdbcTemplate.update(sql, i, random.nextInt(10) + 1, getDate(k), random.nextInt(5) + 1, random.nextInt(1000) + 1);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                        errorCount++;
                    }
                }
            }
        }
        System.out.println("成功次数:" + executeCount + "    " + "失败次数:" + errorCount);
        if (executeCount == errorCount) {
            return new Result(-1, "执行失败");
        } else {
            return new Result(1, "执行成功");
        }
    }

    private int getDate(int index) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, index);
        Date time = calendar.getTime();
        String str = df.format(time);
        long dateMin = Long.valueOf(str);
        return (int) (dateMin / 10000);
    }

}
