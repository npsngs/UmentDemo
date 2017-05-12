//
// Source code recreated from setRequestCallback .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.analytics.aggregate.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarUtil {
    public static final int a = 1;
    private static final int b = 1000;
    private static final int c = 1001;
    private static final int d = 1002;

    public CalendarUtil() {
    }

    public static String a(long timeInMills) {
        byte var2 = 6;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMills);
        int var4 = 60 / var2 * calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) / var2 + 1;
        long var5 = getDurationDays(timeInMills);
        return String.valueOf(var5 * 240L + (long)(var4 - 1));
    }

    public static long getDurationDays(long timeInMills) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
            long startTime = simpleDateFormat.parse("1970").getTime();
            long durationDays = (timeInMills - startTime) / 86400000L;
            durationDays += (timeInMills - startTime) % 86400000L > 0L?1L:0L;
            return durationDays;
        } catch (Throwable throwable) {
            return 0L;
        }
    }

    public static long c(long timeInMills) {
        return a(timeInMills, 1001);
    }

    public static long d(long timeInMills) {
        return a(timeInMills, 1002);
    }

    private static long a(long timeInMills, int var2) {
        byte var3 = 6;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMills);
        int var5 = 60 / var3 * calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) / var3 + 1;
        int second = calendar.get(Calendar.SECOND);
        int var7 = 0;
        if(var2 == 1002) {
            int minute = calendar.get(Calendar.MINUTE);
            byte var9 = 6;
            var7 = var9 * 60 - (minute % var9 * 60 + second);
        } else if(var2 == 1001) {
            var7 = 60 - second % 60;
            if(var5 % 6 == 0) {
                var7 += 60;
            }
        }

        return (long)(var7 * 1000);
    }

    public static boolean isSameDayOfMonth(long timeInMills1, long timeInMills2) {
        int dayOfMonth1 = getDayOfMonth(timeInMills1);
        int dayOfMonth2 = getDayOfMonth(timeInMills2);
        return dayOfMonth1 == dayOfMonth2;
    }

    private static int getDayOfMonth(long timeInMills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMills);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
