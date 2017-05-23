package com.zmin.birthday.mvp.ui.holder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.zmin.birthday.R;
import com.zmin.birthday.mvp.model.entity.Birthday;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/23.
 */

public class BirthdayHolder extends BaseHolder<Birthday> {

    @Nullable
    @BindView(R.id.tv_name)
    TextView tv_name;
    @Nullable
    @BindView(R.id.tv_new_birthday)
    TextView tv_new_birthday;
    @Nullable
    @BindView(R.id.tv_old_birthday)
    TextView tv_old_birthday;


    public BirthdayHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(Birthday data, int position) {
        tv_name.setText(data.getName());
        tv_new_birthday.setText(data.getYear() + data.getMonth() + data.getDay());
        tv_old_birthday.setText(data.getOld_year() + data.getOld_month() + data.getOld_day());
    }
}
