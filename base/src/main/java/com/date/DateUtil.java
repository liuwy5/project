package com.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String now() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
