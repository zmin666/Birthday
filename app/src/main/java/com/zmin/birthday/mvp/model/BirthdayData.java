package com.zmin.birthday.mvp.model;

import com.zmin.birthday.mvp.model.entity.Birthday;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:10
 * @desc:
 */
public class BirthdayData {
    static String[] names = {"及时雨", "托塔天王", "玉麒麟", "豹子头", "黑旋风", "花和尚", "吴用", "行者", "青面兽", "燕青", "孙二娘", "公孙胜", "关胜", "秦明", "呼延灼"};

    static String[] old_years = {"1945", "1948", "1980", "1990", "", "1956", "1986", "1993", "1997", "1997", "1995", "1994", "1992", "1991", "1979"};
    static String[] old_months = {"10", "8", "6", "5", "7", "4", "9", "12", "11", "10", "4", "5", "6", "8", "5"};
    static String[] old_days = {"12", "13", "30", "20", "18", "17", "14", "8", "1", "28", "25", "24", "24", "27", "11"};

    static String[] years = {"1945", "1948", "1980", "1990", "", "1956", "1986", "1993", "1997", "1997", "1995", "1994", "1992", "1991", "1979"};
    static String[] months = {"10", "8", "6", "5", "7", "4", "9", "12", "11", "10", "4", "5", "6", "8", "5"};
    static String[] days = {"12", "13", "30", "20", "18", "17", "14", "8", "1", "28", "25", "24", "24", "27", "11"};


    public static List<Birthday> getData() {
        ArrayList<Birthday> birthdays = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            birthdays.add(new Birthday("001",names[i],"1991-1-1","1990-12-10","1","2","1"));
        }
        return birthdays;
    }
}

