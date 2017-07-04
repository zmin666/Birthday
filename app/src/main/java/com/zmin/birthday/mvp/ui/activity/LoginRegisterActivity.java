package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.app.utils.MD5Utils;
import com.zmin.birthday.di.component.DaggerLoginComponent;
import com.zmin.birthday.di.module.LoginModule;
import com.zmin.birthday.mvp.contract.LoginRegisterContract;
import com.zmin.birthday.mvp.presenter.LoginRegisterPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginRegisterActivity extends BaseActivity<LoginRegisterPresenter> implements LoginRegisterContract.view {

    @BindView(R.id.et_user_num) EditText et_user_num;
    @BindView(R.id.et_user_password) EditText et_user_password;
    @BindView(R.id.ll_login) LinearLayout ll_login;
    @BindView(R.id.ll_rigister) LinearLayout ll_rigister;

    @BindView(R.id.et_usename) EditText et_usename;
    @BindView(R.id.et_pwd) EditText et_pwd;
    @BindView(R.id.et_pwd_agin) EditText et_pwd_agin;
    @BindView(R.id.et_verification) EditText et_verification;

    @OnClick(R.id.login_bt_login)
    public void login_login(View view) {
        Log.i("zmin.............", ".登录操作...");
    }

    @OnClick(R.id.login_bt_register)
    public void login_register(View view) {
        Log.i("zmin.............", ".注册界面...");
        showRegisterView();
    }

    @OnClick(R.id.register_bt_login)
    public void register_login(View view) {
        Log.i("zmin.............", ".登录界面...");
        showLoginView();
    }

    @OnClick(R.id.register_bt_register)
    public void register_register(View view) {
        Log.i("zmin.............", ".注册操作...");
        String userName = et_usename.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        String pwd_agin = et_pwd_agin.getText().toString().trim();
        long time = System.currentTimeMillis();
        if (userName.isEmpty() || pwd.isEmpty() || pwd_agin.isEmpty()) {
            Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
        } else if (!pwd.equals(pwd_agin)) {
            Toast.makeText(this, "两次密码输入不一致,请重输入", Toast.LENGTH_SHORT).show();
        } else {
            Map fields = new HashMap<String, Object>();
            fields.put("time", time);
            fields.put("act", 1);
            fields.put("md5", MD5Utils.getMd5(time + "1"));
            fields.put("username", userName);
            fields.put("password", pwd);
            mPresenter.register(fields);
        }
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
        ll_rigister.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRegisterView() {
        ll_login.setVisibility(View.INVISIBLE);
        ll_rigister.setVisibility(View.VISIBLE);
    }
}
