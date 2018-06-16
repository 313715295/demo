package com.zwq.parent.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * created by zwq on 2018/6/16
 */
public class DateFormatUtil {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateFormat(Instant instant) {
        Date date = Date.from(instant);
        return DATE_FORMAT.format(date);
    }

    public static Instant stringFormat(String source) throws ParseException {
        Date date = DATE_FORMAT.parse(source);
        return date.toInstant();
    }
}
