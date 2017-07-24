package com.zmin.birthday.mvp.ui.activity;


import android.content.Intent;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.di.component.DaggerUserCenterComponent;
import com.zmin.birthday.di.module.UserCenterModule;
import com.zmin.birthday.mvp.contract.UserCenterContract;
import com.zmin.birthday.mvp.presenter.UsercenterPresenter;

import java.util.HashMap;

public class UserCenterActivity extends BaseActivity<UsercenterPresenter> implements UserCenterContract.view {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerUserCenterComponent
                .builder()
                .appComponent(appComponent)
                .userCenterModule(new UserCenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_usercenter;
    }

    @Override
    public void initData() {

    }

    @Override
    public HashMap<String, Object> getBirth() {
        return null;
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
