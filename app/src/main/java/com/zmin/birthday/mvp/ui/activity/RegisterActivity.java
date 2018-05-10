package com.zmin.birthday.mvp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.app.utils.MD5Utils;
import com.zmin.birthday.di.component.DaggerRegisterComponent;
import com.zmin.birthday.di.module.RegisterModule;
import com.zmin.birthday.mvp.contract.RegisterContract;
import com.zmin.birthday.mvp.presenter.RegisterPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.view {

    @BindView(R.id.cv_add) CardView cvAdd;
    @BindView(R.id.fab) FloatingActionButton fab;

    @BindView(R.id.et_username) EditText et_usename;
    @BindView(R.id.et_password) EditText et_pwd;
    @BindView(R.id.et_repeatpassword) EditText et_pwd_agin;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRegisterComponent
                .builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_register;
    }


    @Override
    public void initData() {
        ShowEnterAnimation();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
    }

    @OnClick(R.id.bt_go)
    public void register_register(View view) {
        Log.i("zmin.............", ".注册操作...");
        String userName = et_usename.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        String pwd_agin = et_pwd_agin.getText().toString().trim();
        if (userName.isEmpty() || pwd.isEmpty() || pwd_agin.isEmpty()) {
            Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
        } else if (!pwd.equals(pwd_agin)) {
            Toast.makeText(this, "两次密码输入不一致,请重输入", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.register(getFiels(et_usename, et_pwd, 1));
        }
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
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
