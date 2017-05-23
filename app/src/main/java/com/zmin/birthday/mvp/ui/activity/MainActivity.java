package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.di.component.DaggerMainComponent;
import com.zmin.birthday.di.module.MainModule;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.presenter.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.view {
    @Nullable
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @Nullable
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

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
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mPresenter.requestBirthdayData();
    }

    @Override
    public void setAdapter(DefaultAdapter adapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void addItem() {
        mPresenter.deleteItemData();
    }

    @Override
    public void deleteItem(int position) {
        mPresenter.deleteItemData(position);
    }

    @Override
    public void showLoading() {
        Log.i("zmin.............", "..showLoading..");
    }

    @Override
    public void hideLoading() {
        Log.i("zmin.............", "..hideLoading..");
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
