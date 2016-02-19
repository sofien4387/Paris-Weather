package com.mtp.test.parisweather.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class of date utils used to centralize any method which have relation with date using.
 *
 * E.g:
 * - Date conversion from long type to string.
 *
 * @author Rahmouni Sofien
 */
public class DateUtils {
    /**
     * Convert date timestamp to formatted date .
     * current format date is : <code>"MMM dd, yyyy"</code>
     *
     * @param date time stamp date in second unit.
     * @return formatted date.
     */
    public static String convertDate(long date) {
        Date time = new Date(date * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(time);
    }
}
