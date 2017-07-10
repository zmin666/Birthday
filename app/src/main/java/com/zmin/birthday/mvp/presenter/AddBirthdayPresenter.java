package com.zmin.birthday.mvp.presenter;

import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.app.userpermission.user.UserControl;
import com.zmin.birthday.app.utils.MD5Utils;
import com.zmin.birthday.mvp.contract.AddBirthdayContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;
import com.zmin.birthday.mvp.ui.activity.AddBirthdayActivity;

import java.util.Calendar;
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
 * @date: 2017/5/23 16:48
 * @desc:
 */
@ActivityScope
public class AddBirthdayPresenter extends BasePresenter<AddBirthdayContract.Model, AddBirthdayContract.view> {

    private final Application mApplication;
    private final RxErrorHandler mErrorHandler;
    private final AppManager mAppManager;
    private final AddBirthdayActivity mActivity;
    private Birthday mBirthday;

    @Inject
    public AddBirthdayPresenter(AddBirthdayContract.Model model, AddBirthdayContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
        mActivity = (AddBirthdayActivity) mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void calendar() {
        //弹出日历
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(mActivity, year + "年" + monthOfYear + 1 + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
//                Birthday birthday = new Birthday("张三丰", year, monthOfYear + 1, dayOfMonth, 0, 0, 0);
//                mBirthdays.add(birthday);
//                mMAdapter.notifyItemInserted(mBirthdays.size() - 1);
                //
                String data = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                mRootView.setDate(data);
                //保存到本地数据库
                //savaInDB(birthday);
            }
        }, year, month, day).show();
    }

    public void uploadeData() {
        HashMap<String, Object> map = new HashMap<>();
        String userId = UserControl.getInstance().getCurrentUser(mActivity).getUserId();
        long time = System.currentTimeMillis();
        map.put("time", time);
        map.put("act", 1);
        map.put("md5", MD5Utils.getMd5(time + "1"));
        map.put("o_uid", userId);
        map.putAll(mRootView.getBirth());
        //构造生日个体
        mBirthday = new Birthday(userId
                ,(String) map.get("o_realname")
                , (String) map.get("o_solar_birthday")
                ,(String) map.get("o_prefer_brith")
                ,(String) map.get("o_sex")
                ,(String) map.get("o_sex"));


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
                        if(responseBeen.getCode() == 200){
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
