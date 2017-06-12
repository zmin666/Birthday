package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.di.component.DaggerLoginComponent;
import com.zmin.birthday.di.module.LoginModule;
import com.zmin.birthday.mvp.contract.LoginContract;
import com.zmin.birthday.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.view {

    @BindView(R.id.et_user_num) EditText et_user_num;
    @BindView(R.id.et_user_password) EditText et_user_password;
    @BindView(R.id.ll_login) LinearLayout ll_login;
    @BindView(R.id.ll_rigister) LinearLayout ll_rigister;

    @BindView(R.id.et_usename) EditText et_usename;
    @BindView(R.id.et_pwd) EditText et_pwd;
    @BindView(R.id.et_pwd_agin) EditText et_pwd_agin;
    @BindView(R.id.et_verification) EditText et_verification;


    @OnClick(R.id.bt_login)
    public void login(View view) {
        showLoginView();
        Toast.makeText(this, "点击了登录", Toast.LENGTH_SHORT).show();
        String userName = et_user_num.getText().toString().trim();
        String pwd = et_user_password.getText().toString().trim();
        mPresenter.login(userName, pwd);
    }


    @OnClick(R.id.bt_register)
    public void register(View view) {
        showRegisterView();
        Toast.makeText(this, "点击了注册", Toast.LENGTH_SHORT).show();
        String phoneNum = et_usename.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        String pwd_agin = et_pwd_agin.getText().toString().trim();
        String ver = et_verification.getText().toString().trim();
        mPresenter.register(phoneNum,pwd,pwd_agin,ver);
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        setupView();
    }

    private void setupView() {
        showLoginView();
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

    @Override
    public void showLoginView() {
        ll_login.setVisibility(View.VISIBLE);
        ll_rigister.setVisibility(View.GONE);
    }

    @Override
    public void showRegisterView() {
        ll_login.setVisibility(View.GONE);
        ll_rigister.setVisibility(View.VISIBLE);
    }
}
