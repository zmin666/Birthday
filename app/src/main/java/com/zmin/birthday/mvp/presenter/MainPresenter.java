package com.zmin.birthday.mvp.presenter;

import android.app.Application;
import android.database.Cursor;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.PermissionUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zmin.birthday.app.db.BirthdayHelp;
import com.zmin.birthday.app.db.DbUtil;
import com.zmin.birthday.app.userpermission.user.UserControl;
import com.zmin.birthday.app.utils.FileUtils;
import com.zmin.birthday.app.utils.PermissionUtils;
import com.zmin.birthday.app.utils.TextUtil;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.BithdayBeen;
import com.zmin.birthday.mvp.model.entity.BithdayContactBeen;
import com.zmin.birthday.mvp.ui.activity.MainActivity;
import com.zmin.birthday.mvp.ui.adapter.BirthdayDataAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.view> {

    private final Application mApplication;
    private final RxErrorHandler mErrorHandler;
    private final AppManager mAppManager;
    private final MainActivity mActivity;

    private List<Birthday> mBirthdays = new ArrayList<>();
    private BirthdayDataAdapter mMAdapter;
    private long starTime;
    private RxPermissions mRxPermissions;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.view rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;

        mActivity = (MainActivity) mRootView;
        mRxPermissions = new RxPermissions(mActivity);
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
            mMAdapter = new BirthdayDataAdapter(mBirthdays, mActivity);
            mRootView.setAdapter(mMAdapter);//设置Adapter
        }
        //获取数据  展示数据  保存到数据库中
        Map<String, Object> fileMap = FileUtils.getFileMap(3);
        String userId = UserControl.getInstance().getCurrentUser(mActivity).getUserId();
        fileMap.put("o_uid", userId);

        mModel.getBirthdayData(fileMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BithdayBeen>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        starTime = System.currentTimeMillis();
                    }

                    @Override
                    public void onNext(@NonNull BithdayBeen bithdayBeen) {
                        if (bithdayBeen.getCode() == 200) {
                            List<BithdayBeen.DataBean> data = bithdayBeen.getData();
                            if (data == null || data.size() == 0) {
                                return;
                            } else if (refresh) {
                                mBirthdays.clear();
                                mBirthdays.addAll(changeDate(data));
                            } else {
                                mBirthdays.addAll(changeDate(data));
                            }
                        }
                        long t = 1500 - (System.currentTimeMillis() - starTime);
                        long time = t < 0 ? 0 : t;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mMAdapter.notifyDataSetChanged();
                                mRootView.hideLoading();
                            }
                        }, time);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mRootView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    private List<Birthday> changeDate(List<BithdayBeen.DataBean> data) {
        ArrayList<Birthday> birthdays = new ArrayList<>();
        for (BithdayBeen.DataBean dataBean : data) {
            Birthday birthday = new Birthday();
            birthday.setId(dataBean.getSysid());
            birthday.setBirth(dataBean.getO_solar_birthday());
            birthday.setOld_birth(dataBean.getO_lunar_birthday());
            birthday.setName(dataBean.getO_realname());
            birthday.setPerfer(dataBean.getO_prefer_brith());
            birthday.setSex(dataBean.getO_sex());
            birthday.setIgnoreYear(dataBean.getO_del_year());
            birthdays.add(birthday);
        }

        return birthdays;
    }

    /**
     * 匹配通讯录并处理结果 去掉无效数据和已经有的数据
     * @param data
     * @param beforebirthdays
     * @return
     */
    private List<Birthday> changeContactDate(List<BithdayContactBeen.DataBean> data, List<Birthday> beforebirthdays) {
        ArrayList<Birthday> birthdays = new ArrayList<>();
        for (BithdayContactBeen.DataBean dataBean : data) {
            Birthday birthday = new Birthday();
            birthday.setId(dataBean.getSysid());
            birthday.setBirth(dataBean.getSolar_birthday());
            birthday.setOld_birth(dataBean.getLunar_birthday());
            birthday.setName(dataBean.getUsername());
            birthday.setPerfer(dataBean.getPrefer_brith());
            birthday.setSex(dataBean.getSex());
            birthday.setIgnoreYear("1");
            String tel = dataBean.getTel();
            birthday.setTel(tel);
            if (!TextUtil.checkDate(dataBean.getSolar_birthday()) && !TextUtil.checkDate(dataBean.getLunar_birthday())) {
                continue;
            }
            if (TextUtils.isEmpty(dataBean.getUsername())) {
                continue;
            }
            for (Birthday beforebirthday : beforebirthdays) {
                if (beforebirthday.getTel().equals(tel)) {
                    continue;
                }
            }
            birthdays.add(birthday);
        }

        //提示
        if (birthdays.size() == 0) {
            Toast.makeText(mApplication, "在通讯录中没有匹配到对应的生日", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mApplication, "在通讯录中匹配到了" + birthdays.size() + "个对应的生日", Toast.LENGTH_SHORT).show();
        }

        return birthdays;
    }

    /**
     * 添加生日
     */
    public void addItemData() {


    }

    /**
     * 保存生日信息到数据库
     *
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
     *
     * @param newBirthday
     */
    public void addItemData(Birthday newBirthday) {
        mBirthdays.add(newBirthday);
        mMAdapter.notifyDataSetChanged();
    }


    /**
     * 更新条目
     *
     * @param position
     * @param birthday
     */
    public void updateItemData(int position, Birthday birthday) {
        mBirthdays.remove(position);
        mBirthdays.add(position, birthday);
        mMAdapter.notifyItemChanged(position);
    }

    public void getContacts() {
        PermissionUtils.readContacts(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                Log.i("zmin.............", "..获取通讯录..");
                getContact();//请求权限成功后做一些操作
            }
        }, mRxPermissions, mRootView, mErrorHandler);

    }

    private void getContact() {

        List<String> numberList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = mActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String name = cursor.getString(nameFieldColumnIndex);
            String number = cursor.getString(numberFieldColumnIndex);
            if (number.length() == 11) {
                numberList.add(number);
                nameList.add(name);
            } else if (number.length() > 11) {
                String s1 = number.replace("+86", "");
                String s2 = s1.replace(" ", "");
                numberList.add(s2);
                nameList.add(name);
            }
        }
        cursor.close();
        Log.i("zmin...........联系人名字..", "...." + nameList);
        Log.i("zmin...........联系人电话..", "...." + numberList);
        Gson gson = new Gson();
        String s = gson.toJson(numberList).toString();
        Log.i("zmin.......sss......", "...." + s);
        requestContactData(s);
    }

    public void requestContactData(String contact) {
        mRootView.showLoading();
        if (mMAdapter == null) {
            //  mMAdapter = new BirthdayAdapter(mBirthdays);
            mMAdapter = new BirthdayDataAdapter(mBirthdays, mActivity);
            mRootView.setAdapter(mMAdapter);//设置Adapter
        }
        //获取数据  展示数据  保存到数据库中
        Map<String, Object> fileMap = FileUtils.getFileMap(1);
        String userId = UserControl.getInstance().getCurrentUser(mActivity).getUserId();
        fileMap.put("o_uid", userId);
        fileMap.put("tel_group", contact);
        Log.i("zmin..fileMap...", ".." + fileMap);

        mModel.getContacts(fileMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BithdayContactBeen>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BithdayContactBeen bithdayContactBeen) {
                        if (bithdayContactBeen.getCode() == 200) {
                            List<BithdayContactBeen.DataBean> data = bithdayContactBeen.getData();
                            mBirthdays.addAll(changeContactDate(data,mBirthdays));
                        }
                        mMAdapter.notifyDataSetChanged();
                        mRootView.hideLoading();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mRootView.hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }

                });

    }
}
