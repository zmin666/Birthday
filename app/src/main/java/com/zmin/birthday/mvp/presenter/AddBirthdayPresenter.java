package com.zmin.birthday.mvp.presenter;

import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.R;
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

    public void setBirthday(Birthday birthday) {
        mBirthday = birthday;
    }

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
        new DatePickerDialog(mActivity, R.style.AppThemeAppDate, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String data = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                mRootView.setDate(data);
                //保存到本地数据库
                //savaInDB(birthday);
            }
        }, year, month, day).show();
    }

    /**
     * 更新信息
     *
     * @param act      操作
     * @param position 点击的位置.
     */
    public void uploadeData(String act, int position) {
        if (!mRootView.checkDate()) {
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        String userId = UserControl.getInstance().getCurrentUser(mActivity).getUserId();
        long time = System.currentTimeMillis();
        map.put("time", time);
        map.put("act", act);
        map.put("md5", MD5Utils.getMd5(time + act));
        map.put("o_uid", userId);
        map.putAll(mRootView.getBirth());

        switch (act) {
            case "1":
                break;
            case "4": //删除
                map.put("del_id", mBirthday.getId());
                break;
            case "5": //修改
                map.put("edit_id", mBirthday.getId());
                break;
            default:
                break;
        }


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
                            if ("1".equals(act)) {  //新增
                                //构造生日个体
                                mBirthday = new Birthday(String.valueOf(responseBeen.getId())
                                        , (String) map.get("o_realname")
                                        , (String) map.get("o_lunar_birthday")
                                        , (String) map.get("o_solar_birthday")
                                        , (String) map.get("o_prefer_brith")
                                        , (String) map.get("o_sex")
                                        , (String) map.get("o_del_year"));
                                bundle.putParcelable("NewBirthday", mBirthday);
                                bundle.putInt("action", 1);
                            } else if ("5".equals(act)) { //修改
                                mBirthday = new Birthday(mBirthday.getId()
                                        , (String) map.get("o_realname")
                                        , (String) map.get("o_lunar_birthday")
                                        , (String) map.get("o_solar_birthday")
                                        , (String) map.get("o_prefer_brith")
                                        , (String) map.get("o_sex")
                                        , (String) map.get("o_del_year"));
                                bundle.putInt("action", 5);
                                bundle.putInt("position", position);
                                bundle.putParcelable("NewBirthday", mBirthday);
                            } else if ("4".equals(act)) { //删除
                                bundle.putInt("action", 4);
                                bundle.putInt("position", position);
                            }
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
