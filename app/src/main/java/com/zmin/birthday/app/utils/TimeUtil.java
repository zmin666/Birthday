package com.zmin.birthday.app.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: ZhangMin
 * @date: 2017/7/21 16:08
 * @desc:
 */
public class TimeUtil {


    /**
     * 计算倒计时
     *
     * @param s
     * @return
     */
    public static String getCountdown(String s) {
        String substring = s.substring(4);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        return String.valueOf(daysBetween(dateNowStr, Calendar.getInstance().get(Calendar.YEAR) + substring));
    }


    /**
     * 计算两个日期之间天数差
     *
     * @param smdate
     * @param bdate
     * @return
     */
    public static int daysBetween(String smdate, String bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try {
            cal.setTime(sdf.parse(smdate));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
