package com.yizhuoyang.classroomfeatures.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 用来在数据库中生成假数据的
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateDataTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 用来生成课程表
     */
    @Test
    public void createClassSchedule() {
        String sql = "insert into class_schedule (room_id,subject_id,date,time,teacher_id) values (?,?,?,?,?)";
        int date = getDate();
        Random random = new Random();
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                try {
                    int update = jdbcTemplate.update(sql, i, random.nextInt(10) + 1, date, random.nextInt(5) + 1, random.nextInt(1000) + 1);
                    System.out.println(update);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private int getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        String str = df.format(time);
        long dateMin = Long.valueOf(str);
        return (int) (dateMin / 10000);
    }


}