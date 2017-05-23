package com.zmin.birthday.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.zmin.birthday.mvp.model.entity.Birthday;

import java.util.List;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:34
 * @desc:
 */
public class BirthdayAdapter extends DefaultAdapter<Birthday>{


    public BirthdayAdapter(List<Birthday> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Birthday> getHolder(View v, int viewType) {
        return null;
    }

    @Override
    public int getLayoutId(int viewType) {
        return 0;
    }
}
