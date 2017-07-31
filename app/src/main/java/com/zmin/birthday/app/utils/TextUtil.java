package com.zmin.birthday.app.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2017/7/31.
 */

public class TextUtil {

    public static boolean checkDate(String s) {
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        String[] split = s.split("-");
        if (split.length == 2 || split.length == 3) {
            try {
                int month = Integer.parseInt(split[split.length - 2]);
                int day = Integer.parseInt(split[split.length - 1]);
                if (month > 0 && month < 13 && day > 0 && day < 32) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}
