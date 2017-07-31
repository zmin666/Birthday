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

    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23,
            23, 23, 24, 23, 22};
    private final static String[] constellationArr = new String[]{"摩羯座",
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
            "天蝎座", "射手座", "摩羯座"};


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
        Calendar calendar = Calendar.getInstance();
        int current = calendar.get(Calendar.YEAR);
        int time = daysBetween(dateNowStr, current + substring);
        int day = 0;
        if (time < 0) {
            calendar.set(Calendar.YEAR, current + 1);
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = maxDay + time;
        } else {
            day = time;
        }
        return String.valueOf(day);
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


    /**
     * Java通过生日计算星座
     *
     * @param month
     * @param day
     * @return
     */
    public static String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1]
                : constellationArr[month];
    }

    /**
     * 通过生日计算属相
     *
     * @param year
     * @return
     */
    public static String getZodiac(int year) {
        if (year < 1900) {
            return "未知";
        }
        int start = 1900;
        String[] years = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
                "猴", "鸡", "狗", "猪"};
        return "属: " + years[(year - start) % years.length];
    }

    /**
     * 忽略年份
     *
     * @param birth
     * @return
     */
    public static String ignoreYear(String birth) {
        String[] split = birth.split("-");
        if (split.length == 3) {
            return split[1] + "-" + split[2];
        } else {
            return "";
        }
    }
}
