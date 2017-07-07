package com.zmin.birthday.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:52
 * @desc:
 */
public interface AddBirthdayContract {
    interface view extends IView {
        void getBirth();
    }

    interface Model extends IModel {
        Observable<ResponseBeen> postBirthdayData(Map<String, Object> fields);
    }
}
