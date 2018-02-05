package com.zoker.lib.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by zoker on 2018/1/24.
 */

public class DateUtils {
    //日期格式一
    public static SimpleDateFormat TIME_DEFAULT_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat DAYFORMAT = new SimpleDateFormat("dd", Locale.getDefault());


    public static List<Date> getWeekDate(long timeStamp, boolean isBeginFromSunday) {
        List<Date> weekDates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(timeStamp));
        int week_num = cal.get(Calendar.DAY_OF_WEEK);//日=1，六=7
        if (isBeginFromSunday) {
            //判断是否星期天
            if (week_num != 1) {
                //重置到星期天
                cal.add(Calendar.DAY_OF_MONTH, -week_num + 1);
            }
            //往后记录一个星期的时间
            weekDates.add(cal.getTime());
            for (int i = 1; i < 7; i++) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                weekDates.add(cal.getTime());
            }
        } else {
            //判断是否星期天
            if (week_num != 1) {
                //重置到星期一
                cal.add(Calendar.DAY_OF_MONTH, -week_num + 2);
            } else
                cal.add(Calendar.DAY_OF_MONTH, -6);

            //往后记录一个星期的时间
            weekDates.add(cal.getTime());
            for (int i = 1; i < 7; i++) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                weekDates.add(cal.getTime());
            }
        }
        return weekDates;
    }


    public static List<Date> getWeekDateWithMonth(long timeStamp, boolean isBeginFromSunday) {
        List<Date> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timeStamp));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        long maxTimeStamp = calendar.getTimeInMillis();
        //获取某月最小天数
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);

        while (true) {
            dates.addAll(getWeekDate(calendar.getTimeInMillis(), isBeginFromSunday));
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            boolean isbreaak = dates.get(dates.size() - 1).getTime() > maxTimeStamp;
            if (isbreaak)
                break;
        }
        return dates;
    }

    /**
     * 获取日期工具类
     *
     * @param date 日期
     * @return 工具类
     */
    public static Calendar getCalendar(String date) {
        String[] data = date.split("-");
        int year = Integer.valueOf(data[0]);
        int month = Integer.valueOf(data[1]) - 1;
        int day = Integer.valueOf(data[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal;
    }
}
