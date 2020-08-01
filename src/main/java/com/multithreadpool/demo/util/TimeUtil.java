package com.multithreadpool.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    //得到当前时间，准确到秒
    public static String getTimeNow() {
        DateTimeFormatter df_year = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        String datestr = date.format(df_year);
        return datestr;
    }
    //得到当前时间，准确到毫秒
    public static String getMilliTimeNow() {
        DateTimeFormatter df_year = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime date = LocalDateTime.now();
        String datestr = date.format(df_year);
        return datestr;
    }
}
