package com.zmin.birthday.mvp.presenter;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.app.userpermission.user.UserControl;
import com.zmin.birthday.app.utils.MD5Utils;
import com.zmin.birthday.mvp.contract.UserCenterContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;
import com.zmin.birthday.mvp.ui.activity.UserCenterActivity;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author: ZhangMin
 * @date: 2017/7/24 14:04
 * @desc:
 */
@ActivityScope
public class UsercenterPresenter extends BasePresenter<UserCenterContract.Model, UserCenterContract.view> {
    private final Application mApplication;
    private final RxErrorHandler mErrorHandler;
    private final AppManager mAppManager;
    private final UserCenterActivity mActivity;
    private Birthday mBirthday;

    @Inject
    public UsercenterPresenter(UserCenterContract.Model model, UserCenterContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;

        mActivity = (UserCenterActivity) mRootView;
    }

    public void uploadeData() {
        HashMap<String, Object> map = new HashMap<>();
        String userId = UserControl.getInstance().getCurrentUser(mActivity).getUserId();
        long time = System.currentTimeMillis();
        map.put("time", time);
        map.put("act", 2);
        map.put("md5", MD5Utils.getMd5(time + "2"));
        map.put("o_uid", userId);
        map.putAll(mRootView.getBirth());
        //构造生日个体
        mBirthday = new Birthday(userId
                , (String) map.get("o_realname")
                , (String) map.get("o_lunar_birthday")
                , (String) map.get("o_solar_birthday")
                , (String) map.get("o_prefer_brith")
                , (String) map.get("o_sex"));


        mModel.postBirthdayData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2000)
                .subscribe(new Observer<ResponseBeen>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBeen responseBeen) {
                        mRootView.hideLoading();
                        if (responseBeen.getCode() == 200) {
                            Intent data = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("NewBirthday", mBirthday);
                            data.putExtras(bundle);
                            mActivity.setResult(Activity.RESULT_OK, data);
                            mRootView.killMyself();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
