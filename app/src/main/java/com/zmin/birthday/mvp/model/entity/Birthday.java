package com.zmin.birthday.mvp.model.entity;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:00
 * @desc:
 */
public class Birthday {
    private String name;
    //农历生日
    private String old_year;
    private String old_month;
    private String old_day;
    //阳历生日
    private String year;
    private String month;
    private String day;

    public Birthday(String name, String old_year, String old_month, String old_day, String year, String month, String day) {
        this.name = name;
        this.old_year = old_year;
        this.old_month = old_month;
        this.old_day = old_day;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Birthday(String name, int old_year, int old_month, int old_day, int year, int month, int day) {
        this.name = name;
        this.old_year = String.valueOf(old_year);
        this.old_month = String.valueOf(old_month);
        this.old_day = String.valueOf(old_day);
        this.year = String.valueOf(year);
        this.month = String.valueOf(month);
        this.day = String.valueOf(day);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOld_year() {
        return old_year;
    }

    public void setOld_year(String old_year) {
        this.old_year = old_year;
    }

    public String getOld_month() {
        return old_month;
    }

    public void setOld_month(String old_month) {
        this.old_month = old_month;
    }

    public String getOld_day() {
        return old_day;
    }

    public void setOld_day(String old_day) {
        this.old_day = old_day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
