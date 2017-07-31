package com.zmin.birthday.mvp.model.entity;

/**
 * @author: ZhangMin
 * @date: 2017/7/31 10:33
 * @desc:
 */
public class ResponseAddBeen {

    /**
     * code : 200
     * id : 67
     * msg : 添加成功
     */

    private int code;
    private int id;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
