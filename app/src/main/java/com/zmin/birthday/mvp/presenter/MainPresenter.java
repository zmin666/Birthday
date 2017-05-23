package com.zmin.birthday.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.ui.adapter.BirthdayAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 16:48
 * @desc:
 */
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.view> {

    private final Application mApplication;
    private final RxErrorHandler mErrorHandler;
    private final AppManager mAppManager;

    private List<Birthday> mBirthdays = new ArrayList<>();
    private BirthdayAdapter mMAdapter;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }

    /**
     * 获取数据展示数据
     */
    public void requestBirthdayData(){
        if (mMAdapter == null) {
            mMAdapter = new BirthdayAdapter(mBirthdays);
            mRootView.setAdapter(mMAdapter);//设置Adapter
        }

        //获取数据  展示数据
        List<Birthday> birthdayData = mModel.getBirthdayData();
        mBirthdays.addAll(birthdayData);
        mMAdapter.notifyDataSetChanged();
    }

    /**
     *  添加生日
     */
    public void addItemData(){
        Birthday birthday = new Birthday("张三丰", "1901", "7", "1", "1901", "6", "7");
        mBirthdays.add(birthday);
        mMAdapter.notifyItemInserted(mBirthdays.size()-1);
    }


    /**
     *  删除生日
     */
    public void addItemData(int position){
        mBirthdays.remove(position);
        mMAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
