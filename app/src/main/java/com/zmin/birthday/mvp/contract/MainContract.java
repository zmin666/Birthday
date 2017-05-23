package com.zmin.birthday.mvp.contract;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zmin.birthday.mvp.model.entity.Birthday;

import java.util.List;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:52
 * @desc:
 */
public interface MainContract {
    interface view extends IView {
        void setAdapter(DefaultAdapter adapter);

        void addItem();

        void deleteItem(int position);
    }

    interface Model extends IModel {
        List<Birthday> getBirthdayData();
    }
}
