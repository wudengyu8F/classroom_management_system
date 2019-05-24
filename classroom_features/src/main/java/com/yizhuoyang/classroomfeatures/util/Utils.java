package com.yizhuoyang.classroomfeatures.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static int getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        String str = df.format(time);
        long dateMin = Long.valueOf(str);
        return (int) (dateMin / 10000);
    }

    public static String getTimeMapping(int key) {
        Map<Integer, String> map = new HashMap<Integer, String>(16) {
            {
                put(1, "1,2节");
                put(2, "3,4节");
                put(3, "5,6节");
                put(4, "7,8节");
                put(5, "9,10节");
            }
        };
        return map.get(key);
    }

    public static String getCodeMapping(int key){
        Map<Integer, String> map = new HashMap<Integer, String>(16) {
            {
                put(0, "未通过");
                put(1, "通过");
                put(2, "取消申请");
                put(3, "驳回");
            }
        };
        return map.get(key);
    }


}
