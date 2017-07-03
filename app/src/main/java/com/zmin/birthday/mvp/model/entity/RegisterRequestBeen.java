package com.zmin.birthday.mvp.model.entity;

/**
 * @author: ZhangMin
 * @date: 2017/7/3 17:11
 * @desc:
 */
public class RegisterRequestBeen {
    private long time;
    private int act;
    private String md5;
    private String username;
    private String password;

    public RegisterRequestBeen(long time, int act, String md5, String username, String password) {
        this.time = time;
        this.act = act;
        this.md5 = md5;
        this.username = username;
        this.password = password;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getAct() {
        return act;
    }

    public void setAct(int act) {
        this.act = act;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
