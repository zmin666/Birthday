package com.zmin.birthday.mvp.model.entity;

/**
 * @author: ZhangMin
 * @date: 2017/6/13 11:04
 * @desc:
 */
public class Loginer {
    public String username;
    public String password;
    public String act;
    public String time;
    public String md5;


    public Loginer(String username, String password, String act, String time, String md5) {
        this.username = username;
        this.password = password;
        this.act = act;
        this.time = time;
        this.md5 = md5;
    }
}
