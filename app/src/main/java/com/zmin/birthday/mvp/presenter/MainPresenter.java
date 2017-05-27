package com.zmin.birthday.mvp.presenter;

import android.app.Application;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Toast;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.ui.activity.MainActivity;
import com.zmin.birthday.mvp.ui.adapter.BirthdayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
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
    private final MainActivity mActivity;

    private List<Birthday> mBirthdays = new ArrayList<>();
    private BirthdayAdapter mMAdapter;

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
            mMAdapter = new BirthdayAdapter(mBirthdays);
            mRootView.setAdapter(mMAdapter);//设置Adapter
        }
        //获取数据  展示数据
        List<Birthday> birthdayData = mModel.getBirthdayData();
        if (refresh) {
            mBirthdays.clear();
        }
        mBirthdays.addAll(birthdayData);
        mMAdapter.notifyDataSetChanged();
        mRootView.hideLoading();

    }

    /**
     * 添加生日
     */
    public void addItemData() {
        //弹出日历
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(mActivity, year + "年" + monthOfYear + 1 + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                Birthday birthday = new Birthday("张三丰", year, monthOfYear + 1, dayOfMonth, 0, 0, 0);
                mBirthdays.add(birthday);
                mMAdapter.notifyItemInserted(mBirthdays.size() - 1);
            }
        }, year, month, day).show();
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
}
