package com.zmin.birthday.app.userpermission.permission;

import java.util.Arrays;
import java.util.List;


/**
 * @author: ZhangMin
 * @date:  2017/6/7 16:15
 * @desc: 配置操作所需要的权限
 */
public enum OperateRequesetRole {

    /** 注册人可以做的操作 */
    login(1, Arrays.asList(RoleConfig.ROLE_LOGIN))
    /** 认证人可以做的操作  */
    , identance(2, Arrays.asList(RoleConfig.ROLE_LOGIN, RoleConfig.ROLE_IDENTANCE))
    /** 保证金资格可以做的操作 */
    , assurance(3, Arrays.asList(RoleConfig.ROLE_LOGIN,RoleConfig.ROLE_IDENTANCE, RoleConfig.ROLE_ASSURANCE));

    private int mCode;

    private List<String> mList;

    OperateRequesetRole(int code, List<String> list) {
        mCode = code;
        mList = list;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }
}
