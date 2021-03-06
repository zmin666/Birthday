package com.zmin.birthday.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.app.userpermission.user.User;
import com.zmin.birthday.app.userpermission.user.UserControl;
import com.zmin.birthday.app.utils.MD5Utils;
import com.zmin.birthday.mvp.contract.RegisterContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.LoginBeen;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;
import com.zmin.birthday.mvp.ui.activity.MainActivity;
import com.zmin.birthday.mvp.ui.activity.RegisterActivity;
import com.zmin.birthday.mvp.ui.adapter.BirthdayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:48
 * @desc:
 */
@ActivityScope
public class RegisterPresenter extends BasePresenter<RegisterContract.Model, RegisterContract.view> {

    private final Application mApplication;
    private final RxErrorHandler mErrorHandler;
    private final AppManager mAppManager;
    private final RegisterActivity mActivity;

    private List<Birthday> mBirthdays = new ArrayList<>();
    private BirthdayAdapter mMAdapter;

    @Inject
    public RegisterPresenter(RegisterContract.Model model, RegisterContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;

        mActivity = (RegisterActivity) mRootView;
    }


    @DebugLog
    public void register(Map<String, Object> fields) {
        mModel.register(fields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBeen>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBeen registerBeen) {
                        if (registerBeen.getCode() == 200) {
                            Toast.makeText(mApplication, registerBeen.getMsg(), Toast.LENGTH_SHORT).show();
                            long time = System.currentTimeMillis();
                            fields.put("act", 2);
                            fields.put("time", time);
                            fields.put("md5", MD5Utils.getMd5(time + "2"));
                            login(fields);
                        } else if (registerBeen.getCode() == 300) {
                            Toast.makeText(mApplication, registerBeen.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(mApplication, "注册失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @DebugLog
    public void login(Map<String, Object> fields) {
        mModel.login(fields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBeen>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("zmin...onSubscribe..", "....");
                    }

                    @Override
                    public void onNext(@NonNull LoginBeen loginBeen) {
                        if (loginBeen.getCode() == 200) {
                            Toast.makeText(mApplication, loginBeen.getMsg(), Toast.LENGTH_SHORT).show();
                            User currentUser = UserControl.getInstance().getCurrentUser(mActivity);
                            currentUser.setUser(loginBeen.getData());
                            UserControl.getInstance().setUserToXml(mActivity, currentUser);
                            mRootView.launchActivity(new Intent(mActivity, MainActivity.class));
                            mRootView.killMyself();
                        } else if (loginBeen.getCode() == 300) {
                            Toast.makeText(mApplication, loginBeen.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("zmin.............", "...." + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
