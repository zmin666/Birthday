package com.zmin.birthday.mvp.presenter;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.mvp.contract.LoginRegisterContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.LoginBeen;
import com.zmin.birthday.mvp.model.entity.RegisterBeen;
import com.zmin.birthday.mvp.ui.activity.LoginRegisterActivity;
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
public class LoginRegisterPresenter extends BasePresenter<LoginRegisterContract.Model, LoginRegisterContract.view> {

    private final Application mApplication;
    private final RxErrorHandler mErrorHandler;
    private final AppManager mAppManager;
    private final LoginRegisterActivity mActivity;

    private List<Birthday> mBirthdays = new ArrayList<>();
    private BirthdayAdapter mMAdapter;

    @Inject
    public LoginRegisterPresenter(LoginRegisterContract.Model model, LoginRegisterContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;

        mActivity = (LoginRegisterActivity) mRootView;
    }

    @DebugLog
    public void login(Map<String, Object> fields) {
        mModel.login(fields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBeen>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBeen loginBeen) {
                        if (loginBeen.getCode() == 200) {
                            Toast.makeText(mApplication, loginBeen.getMsg(), Toast.LENGTH_SHORT).show();
                        } else if (loginBeen.getCode() == 300) {
                            Toast.makeText(mApplication, loginBeen.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("zmin.............","...." + e.toString() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @DebugLog
    public void register(Map<String, Object> fields) {
        mModel.register(fields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBeen>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBeen registerBeen) {
                        if (registerBeen.getCode() == 200) {
                            Toast.makeText(mApplication, registerBeen.getMsg(), Toast.LENGTH_SHORT).show();
                        } else if (registerBeen.getCode() == 300) {
                            Toast.makeText(mApplication, registerBeen.getMsg(), Toast.LENGTH_SHORT).show();
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
