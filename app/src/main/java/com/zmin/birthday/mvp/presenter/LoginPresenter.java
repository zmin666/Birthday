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
import com.zmin.birthday.mvp.contract.LoginContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.LoginBeen;
import com.zmin.birthday.mvp.ui.activity.LoginActivity;
import com.zmin.birthday.mvp.ui.activity.MainActivity;
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
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.view> {

    private final Application mApplication;
    private final RxErrorHandler mErrorHandler;
    private final AppManager mAppManager;
    private final LoginActivity mActivity;

    private List<Birthday> mBirthdays = new ArrayList<>();
    private BirthdayAdapter mMAdapter;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;

        mActivity = (LoginActivity) mRootView;
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
