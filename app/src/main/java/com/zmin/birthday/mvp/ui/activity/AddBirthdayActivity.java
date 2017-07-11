package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.app.utils.LunarCalendar;
import com.zmin.birthday.di.component.DaggerAddBirthdayComponent;
import com.zmin.birthday.di.module.AddBirthdayModule;
import com.zmin.birthday.mvp.contract.AddBirthdayContract;
import com.zmin.birthday.mvp.presenter.AddBirthdayPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: ZhangMin
 * @date: 2017/7/7 17:18
 * @desc:
 */
public class AddBirthdayActivity extends BaseActivity<AddBirthdayPresenter> implements AddBirthdayContract.view {


    @BindView(R.id.name) EditText mName;
    @BindView(R.id.rb_sex_man) RadioButton mRbSexMan;
    @BindView(R.id.rb_sex_woman) RadioButton mRbSexWoman;
    @BindView(R.id.rb_male) RadioButton mRbMale;
    @BindView(R.id.rb_lunar) RadioButton mRbLunar;
    @BindView(R.id.et_date) EditText mEtDate;
    @BindView(R.id.image_select_date) ImageButton mImageSelectDate;
    @BindView(R.id.rb_remine_0) RadioButton mRbRemine0;
    @BindView(R.id.rb_remine_1) RadioButton mRbRemine1;
    @BindView(R.id.rb_remine_3) RadioButton mRbRemine3;
    @BindView(R.id.rb_note) RadioButton mRbNote;
    @BindView(R.id.rb_inform) RadioButton mRbInform;
    @BindView(R.id.fl_loading) FrameLayout mFlloading;

    @OnClick(R.id.bt_add)
    public void onViewAddClicked() {
        showLoading();
        mPresenter.uploadeData();
    }

    @OnClick(R.id.image_select_date)
    public void onViewClicked() {
        selectDate();
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAddBirthdayComponent
                .builder()
                .appComponent(appComponent)
                .addBirthdayModule(new AddBirthdayModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_addbirthday;
    }

    @Override
    public void initData() {

    }

    @Override
    public HashMap<String, Object> getBirth() {
        HashMap<String, Object> map = new HashMap<>();
        String name = mName.getText().toString().trim();
        map.put("o_realname", name);
        if (mRbSexMan.isChecked()) {
            map.put("o_sex", "1");
        } else if (mRbSexWoman.isChecked()) {
            map.put("o_sex", "2");
        }
        //1农历 2阳历
        String date = mEtDate.getText().toString().trim();
        String[] split = date.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);

        if (mRbMale.isChecked()) {
            map.put("o_prefer_brith", "1");
            map.put("o_lunar_birthday", date);
            //农历 --> 转成阳历
            LunarCalendar.Solar solar = LunarCalendar.lunarToSolar(new LunarCalendar.Lunar(day, month, year));
            map.put("o_solar_birthday", solar.solarYear + "-" + solar.solarMonth + "-" + solar.solarDay);
        } else if (mRbLunar.isChecked()) {
            map.put("o_prefer_brith", "2");
            map.put("o_solar_birthday", date);
            //阳历 --> 转成农历
            LunarCalendar.Lunar lunar = LunarCalendar.SolarToLunar(new LunarCalendar.Solar(day, month, year));
            map.put("o_lunar_birthday", lunar.lunarYear + "-" + lunar.lunarMonth + "-" + lunar.lunarDay);
        }
        return map;
    }

    @Override
    public void selectDate() {
        mPresenter.calendar();
    }

    @Override
    public void setDate(String date) {
        mEtDate.setText(date);
    }

    @Override
    public void showLoading() {
        mFlloading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mFlloading.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {
        finish();
    }

}
