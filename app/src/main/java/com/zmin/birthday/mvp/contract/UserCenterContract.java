package com.zmin.birthday.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author: ZhangMin
 * @date: 2017/7/24 13:53
 * @desc:
 */
public interface UserCenterContract {
    interface view extends IView {
        HashMap<String,Object> getBirth();
    }

    interface Model extends IModel {
        Observable<ResponseBeen> postBirthdayData(Map<String, Object> fields);
    }
}
