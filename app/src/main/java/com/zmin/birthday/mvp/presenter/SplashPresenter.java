package com.zmin.birthday.mvp.presenter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Intent;
import android.widget.ImageView;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.app.userpermission.user.UserControl;
import com.zmin.birthday.mvp.contract.SplashContract;
import com.zmin.birthday.mvp.ui.activity.LoginActivity;
import com.zmin.birthday.mvp.ui.activity.MainActivity;
import com.zmin.birthday.mvp.ui.activity.SplashActivity;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:48
 * @desc:
 */
@ActivityScope
public class SplashPresenter extends BasePresenter<BaseModel, SplashContract.view> {

    private final Application mApplication;
    private final RxErrorHandler mErrorHandler;
    private final AppManager mAppManager;

    @Inject
    public SplashPresenter(BaseModel model, SplashContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 图片缩放效果
     *
     * @param imageView
     */
    public void scaleImage(ImageView imageView) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView, "ScaleX", new float[]{1.0f, 1.5f});
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "ScaleY", new float[]{1.0f, 1.5f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.setDuration(2000);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                String userId = UserControl.getInstance().getCurrentUser(mApplication).getUserId();
                Intent intent =  new Intent();
                if (userId == null || userId.equals("0")) {
//                    intent = new Intent((SplashActivity) mRootView, LoginRegisterActivity.class);
                    intent = new Intent((SplashActivity) mRootView, LoginActivity.class);
                } else {
                    intent = new Intent((SplashActivity) mRootView, MainActivity.class);
                }
                mRootView.launchActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
