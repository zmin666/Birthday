package com.zmin.birthday.app.userpermission.user;

import java.io.Serializable;

/**
 * @author: ZhangMin
 * @date:  2017/6/7 16:12
 * @desc: 户信息存储
 * 用户的属性分影响权限的和不影响权限的属性
 */

// userId   donate  name  phoneNum password  preferData
// old_year  old_month  old_day   year  month  day

public class User implements Serializable {

    /********************************影响权限的属性************************************************/
    /** 用户id  是否登录 */
    private String userId = "0";
    /** 捐赠钱数 */
    private String donate = "0";
    /**********************************不影响权限的属性**********************************************/
    private String name;
    private String phoneNum; //账号
    private String password; //明文密码

    //生日偏好,1农历 2阳历 3农历阳历都过
    private String preferData;
    //农历生日
    private String old_year;
    private String old_month;
    private String old_day;
    //阳历生日
    private String year;
    private String month;
    private String day;
    /**********************************其他**********************************************/
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDonate() {
        return donate;
    }

    public void setDonate(String donate) {
        this.donate = donate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferData() {
        return preferData;
    }

    public void setPreferData(String preferData) {
        this.preferData = preferData;
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
