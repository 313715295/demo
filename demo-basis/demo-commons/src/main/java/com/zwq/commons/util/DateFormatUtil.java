package com.zwq.commons.util;

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
    private static final DateTimeFormatter STRING_FORMAT = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

    public static String dateFormat(Instant instant) {
        //CartController的confirmOrder方法中有session存储order数据，会序列化order，这时候instant还没设置为null会导致空指针
        if (instant==null) return null;
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(STRING_FORMAT);
    }

    public static Instant stringFormat(String source) {
        LocalDateTime localDateTime = LocalDateTime.parse(source, DATE_FORMAT);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }


}
