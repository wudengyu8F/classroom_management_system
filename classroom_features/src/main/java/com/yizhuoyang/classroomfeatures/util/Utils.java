package com.yizhuoyang.classroomfeatures.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static int getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        String str = df.format(time);
        long dateMin = Long.valueOf(str);
        return (int) (dateMin / 10000);
    }


}
