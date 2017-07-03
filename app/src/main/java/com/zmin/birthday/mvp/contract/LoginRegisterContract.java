package com.zmin.birthday.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zmin.birthday.mvp.model.entity.Loginer;
import com.zmin.birthday.mvp.model.entity.RegisterBeen;

import io.reactivex.Observable;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:52
 * @desc:
 */
public interface LoginRegisterContract {
    interface view extends IView {
        void showLoginView();

        void showRegisterView();
    }

    interface Model extends IModel {
        //注册
        Observable<RegisterBeen> register(String userName, String pwd);
        //登录
        Observable login(Loginer loginer);
    }
}
