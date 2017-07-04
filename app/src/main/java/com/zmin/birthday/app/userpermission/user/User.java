package com.zmin.birthday.app.userpermission.user;

import com.zmin.birthday.mvp.model.entity.LoginBeen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author: ZhangMin
 * @date: 2017/6/7 16:12
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
    private ArrayList<String> permission = new ArrayList<>();
    /**********************************不影响权限的属性**********************************************/
    private String userName;  //账号
    private String name;  //真名

    private String phoneNum; //手机号码
    private String password; //明文密码
    private String image;//头像图片

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

    public void setUser(LoginBeen.DataBean dataBean) {
        this.userId = dataBean.getSysid();
        this.donate = dataBean.getSupply_money();

        this.name = dataBean.getRealname();
        this.userName = dataBean.getUsername();
        this.phoneNum = dataBean.getTel();
        this.password = dataBean.getPassword();
        //  this.image = dataBean.getSysid();
        this.preferData = dataBean.getPrefer_brith();

        //生日
        String lunar_birthday = dataBean.getLunar_birthday();
        String[] lunar = lunar_birthday.split("-");
        this.old_year = lunar[0];
        this.old_month = lunar[1];
        this.old_day = lunar[2];

        String solar_birthday = dataBean.getSolar_birthday();
        String[] solar = solar_birthday.split("-");
        this.year = solar[0];
        this.month = solar[1];
        this.day = solar[2];

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

    public ArrayList<String> getPermission() {
        return permission;
    }

    public void setPermission(ArrayList<String> permission) {
        this.permission = permission;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", donate='" + donate + '\'' +
                ", permission=" + permission +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", preferData='" + preferData + '\'' +
                ", old_year='" + old_year + '\'' +
                ", old_month='" + old_month + '\'' +
                ", old_day='" + old_day + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
