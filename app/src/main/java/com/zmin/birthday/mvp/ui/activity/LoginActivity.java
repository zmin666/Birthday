package com.zmin.birthday.mvp.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.app.utils.MD5Utils;
import com.zmin.birthday.di.component.DaggerLoginComponent;
import com.zmin.birthday.di.module.LoginModule;
import com.zmin.birthday.mvp.contract.LoginContract;
import com.zmin.birthday.mvp.presenter.LoginPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.view {

    @BindView(R.id.et_user_num) EditText etUsername;
    @BindView(R.id.et_user_password) EditText etPassword;
    @BindView(R.id.bt_go) Button btGo;
    @BindView(R.id.cv) CardView cv;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return R.layout.activity_logins;
    }

    @Override
    public void initData() {
        initView();
        setListener();
    }

    private void setListener() {
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Explode explode = new Explode();
                explode.setDuration(500);

                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);

                mPresenter.login(getFiels(etUsername, etPassword, 2));

//                Intent i2 = new Intent(LoginActivity.this,LoginSuccessActivity.class);
//                startActivity(i2, oc2.toBundle());
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, fab, fab.getTransitionName());
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class), options.toBundle());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fab.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
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
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    /**
     * 获取参数
     *
     * @param etName
     * @param etPwd
     * @return
     */
    private Map getFiels(EditText etName, EditText etPwd, int act) {
        String userName = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        long time = System.currentTimeMillis();
        Map fields = new HashMap<String, Object>();
        fields.put("time", time);
        fields.put("act", act);
        fields.put("md5", MD5Utils.getMd5(time + "" + act));
        fields.put("username", userName);
        fields.put("password", pwd);
        return fields;
    }
}
