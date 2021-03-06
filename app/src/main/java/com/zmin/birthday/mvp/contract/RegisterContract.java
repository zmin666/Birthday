package com.zmin.birthday.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zmin.birthday.mvp.model.entity.LoginBeen;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:52
 * @desc:
 */
public interface RegisterContract {
    interface view extends IView {
    }

    interface Model extends IModel {
        //注册
        Observable<ResponseBeen> register(Map<String, Object> fields);

        //登录
        Observable<LoginBeen> login(Map<String, Object> fields);
    }
}
