package com.zmin.birthday.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.app.db.BirthdayHelp;
import com.zmin.birthday.app.db.DbUtil;
import com.zmin.birthday.app.utils.RxUtils;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.MovieEntity;
import com.zmin.birthday.mvp.ui.activity.MainActivity;
import com.zmin.birthday.mvp.ui.adapter.BirthdayDataAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

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
    private final MainActivity mActivity;

    private List<Birthday> mBirthdays = new ArrayList<>();
    private BirthdayDataAdapter mMAdapter;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;

        mActivity = (MainActivity) mRootView;
    }

    /**
     * 获取数据展示数据
     *
     * @param refresh ture先清空现有的数据  刷新的时候和首次加载的时候为ture
     */
    public void requestBirthdayData(boolean refresh) {
        mRootView.showLoading();
        if (mMAdapter == null) {
          //  mMAdapter = new BirthdayAdapter(mBirthdays);
            mMAdapter = new BirthdayDataAdapter(mBirthdays,mActivity);
            mRootView.setAdapter(mMAdapter);//设置Adapter
        }
        //获取数据  展示数据  保存到数据库中
        List<Birthday> birthdayData = mModel.getBirthdayData();
        if (refresh) {
            mBirthdays.clear();
        }
        mBirthdays.addAll(birthdayData);
        mMAdapter.notifyDataSetChanged();
        mRootView.hideLoading();

    }

    public void getUsers(){
        mModel.getUsers(0,10)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .retryWhen(new RetryWithDelay(3, 2))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MovieEntity movieEntity) {
                        Log.i("zmin.............",".11..." +  movieEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("zmin.............",".22..." +  e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 添加生日
     */
    public void addItemData() {


    }

    /**
     * 保存生日信息到数据库
     * @param birthday
     */
    private void savaInDB(Birthday birthday) {
        BirthdayHelp driverHelper = DbUtil.getDriverHelper();
        int size = driverHelper.queryAll().size();
        birthday.setId(String.valueOf(size));
        driverHelper.save(birthday);
    }

    /**
     * 删除生日
     */
    public void deleteItemData(int position) {
        mBirthdays.remove(position);
        mMAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 添加条目
     * @param newBirthday
     */
    public void addItemData(Birthday newBirthday) {
        mBirthdays.add(newBirthday);
        mMAdapter.notifyDataSetChanged();
    }
}
