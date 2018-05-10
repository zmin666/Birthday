package com.zmin.birthday.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zmin.birthday.mvp.model.entity.LoginBeen;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:52
 * @desc:
 */
public interface LoginContract {
    interface view extends IView {
    }

    interface Model extends IModel {
        //登录
        Observable<LoginBeen> login(Map<String, Object> fields);
    }
}
