package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.di.module.MainModule;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.view {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return 0;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setAdapter(DefaultAdapter adapter) {

    }

    @Override
    public void addItem() {

    }

    @Override
    public void deleteItem(int position) {

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
