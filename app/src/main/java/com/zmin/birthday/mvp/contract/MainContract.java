package com.zmin.birthday.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.BithdayBeen;
import com.zmin.birthday.mvp.model.entity.MovieEntity;
import com.zmin.birthday.mvp.ui.adapter.BirthdayDataAdapter;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:52
 * @desc:
 */
public interface MainContract {
    interface view extends IView {
        void setAdapter(BirthdayDataAdapter adapter);

        void deleteItem(int position);

        void updateItem(int position, Birthday birthday);
    }

    interface Model extends IModel {
        Observable<BithdayBeen> getBirthdayData(Map<String, Object> fields);

        Observable<MovieEntity> getUsers(int star, int end);
    }
}
