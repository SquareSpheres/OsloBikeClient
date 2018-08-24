package io.github.squarespheres.oslobikes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    /**
     * Get absolute time difference in seconds between two {@link Date}
     *
     * @param d1 first date
     * @param d2 second date
     * @return time difference in seconds
     */
    public static long TimeDiffInSeconds(Date d1, Date d2) {
        return Math.abs(d1.getTime() - d2.getTime()) / 1000 % 60;
    }

    /**
     * Get time now
     *
     * @return time now
     */
    public static Date TimeNow() {
        return new Date();
    }

    /**
     * Parse date string into {@link Date}. Assumed format is yyyy-MM-ddTHH:mm:ss+HH:mm
     *
     * @param time date string
     * @return date
     */
    public static Date TimeFromString(String time) {
        String[] dateString = time.split("T");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX");
        try {
            return format.parse(dateString[0] + " " + dateString[1]);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time string", e);
        }
    }
}
