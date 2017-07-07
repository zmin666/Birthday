package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.di.module.AddBirthdayModule;
import com.zmin.birthday.mvp.contract.AddBirthdayContract;
import com.zmin.birthday.mvp.presenter.AddBirthdayPresenter;

/**
 * @author: ZhangMin
 * @date: 2017/7/7 17:18
 * @desc:
 */
public class AddBirthdayActivity extends BaseActivity<AddBirthdayPresenter> implements AddBirthdayContract.view {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAddBirthdayComponent
                .builder()
                .appComponent(appComponent)
                .addBirthdayModule(new AddBirthdayModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_addbirthday;
    }

    @Override
    public void initData() {

    }

    @Override
    public void getBirth() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}
