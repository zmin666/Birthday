package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.di.component.DaggerSplashComponent;
import com.zmin.birthday.di.module.SplashModule;
import com.zmin.birthday.mvp.contract.SplashContract;
import com.zmin.birthday.mvp.presenter.SplashPresenter;

import butterknife.BindView;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.view {

    @BindView(R.id.iv) ImageView mIv;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSplashComponent
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        ScaleImage();
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
        startActivity(intent);
        finish();
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void ScaleImage() {
       mPresenter.scaleImage(mIv);
    }
}
