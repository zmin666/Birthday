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
import com.zmin.birthday.di.component.DaggerLoginComponent;
import com.zmin.birthday.di.module.LoginModule;
import com.zmin.birthday.mvp.contract.LoginRegisterContract;
import com.zmin.birthday.mvp.model.entity.Loginer;
import com.zmin.birthday.mvp.presenter.LoginRegisterPresenter;

import java.math.BigInteger;
import java.security.MessageDigest;

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


    //登录
    @OnClick(R.id.bt_login)
    public void login(View view) {
        showLoginView();
        Toast.makeText(this, "点击了登录", Toast.LENGTH_SHORT).show();
        String userName = et_user_num.getText().toString().trim();
        String pwd = et_user_password.getText().toString().trim();
        long time = System.currentTimeMillis();
        String md5 = getMD5(time + "2");
        Loginer loginer = new Loginer("xxd123456", "6748129", "2", String.valueOf(time), md5);
        mPresenter.login(loginer);
    }


    //注册
    @OnClick(R.id.bt_register)
    public void register(View view) {
        showRegisterView();
        long time = System.currentTimeMillis();
        String md5 = getMD5(time + "2");
        Log.i("zmin......time.......", "...." + time);
        Log.i("zmin......md5.......", "...." + getMD5(time + "2"));


        String userName = et_usename.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        String pwd_agin = et_pwd_agin.getText().toString().trim();
        if (pwd.equals(pwd_agin)) {
            mPresenter.register(userName, pwd);
        } else {
            Toast.makeText(this,"两次密码输入不一致,请重输入",Toast.LENGTH_SHORT).show();
        }


    }

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            System.out.println("aaaaaaaaaaaaaaa:" + str);
            System.out.println("aaaaaaaaaaaaaaa:" + md.digest());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
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
        ll_rigister.setVisibility(View.GONE);
    }

    @Override
    public void showRegisterView() {
        ll_login.setVisibility(View.GONE);
        ll_rigister.setVisibility(View.VISIBLE);
    }
}
