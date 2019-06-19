package com.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtil {
    public static String timeStamp2Date(String millSeconds) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(millSeconds)));
    }

    public static Long getTime(String timeString) {
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = format.parse(timeString);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static void main(String[] args) {
        System.out.println(timeStamp2Date("1559013959311"));
        System.out.println(timeStamp2Date("1558022400000"));
        System.out.println(getTime("2019-06-04 14:37"));
        System.out.println(getTime("2019-06-04 14:38"));
    }
}
