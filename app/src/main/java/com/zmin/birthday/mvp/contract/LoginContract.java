package com.zmin.birthday.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:52
 * @desc:
 */
public interface LoginContract {
    interface view extends IView {

    }

    interface Model extends IModel {
        //注册
        void register();
        //登录
        void login(int userName, int pwd);
    }
}
