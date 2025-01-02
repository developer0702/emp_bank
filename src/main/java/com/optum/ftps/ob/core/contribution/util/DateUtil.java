package com.optum.ftps.ob.core.contribution.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** Date related utilities */
@Slf4j
public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Date parse(String date, String format) {
        try {
            var dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            return dateFormat.parse(date);
        } catch (Exception e) {
            log.error("Unable to parse date : {}", e.getMessage());
            return null;
        }
    }

    public static String format(Date date, String format) {
        var dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date getCurrentDate() {
        var cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
}
