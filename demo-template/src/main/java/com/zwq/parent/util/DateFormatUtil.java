package com.zwq.parent.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * created by zwq on 2018/6/16
 */
public class DateFormatUtil {

    //非线程安全的 弃用
    //private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    private static final DateTimeFormatter STRING_FORMAT = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss a");

    public static String dateFormat(Instant instant) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DATE_FORMAT);
    }

    public static Instant stringFormat(String source) {
        LocalDateTime localDateTime = LocalDateTime.parse(source, DATE_FORMAT);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }


}
