package com.zmin.birthday.mvp.ui.activity;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.app.utils.LunarCalendar;
import com.zmin.birthday.di.component.DaggerUserCenterComponent;
import com.zmin.birthday.di.module.UserCenterModule;
import com.zmin.birthday.mvp.contract.UserCenterContract;
import com.zmin.birthday.mvp.presenter.UsercenterPresenter;

import java.util.HashMap;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserCenterActivity extends BaseActivity<UsercenterPresenter> implements UserCenterContract.view {


    @BindView(R.id.fl_loading) FrameLayout mFlLoading;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.imageView) CircleImageView mImageView;
    @BindView(R.id.name) EditText mName;
    @BindView(R.id.rb_sex_man) RadioButton mRbSexMan;
    @BindView(R.id.rb_sex_woman) RadioButton mRbSexWoman;
    @BindView(R.id.phone) EditText mPhone;
    @BindView(R.id.rb_male) RadioButton mRbMale;
    @BindView(R.id.rb_lunar) RadioButton mRbLunar;
    @BindView(R.id.rb_ignore_year) CheckBox mRbIgnoreYear;
    @BindView(R.id.textView) TextView mTextView;
    @BindView(R.id.et_date) EditText mEtDate;
    @BindView(R.id.image_select_date) ImageView mImageSelectDate;
    @BindView(R.id.rb_note) RadioButton mRbNote;
    @BindView(R.id.rb_inform) RadioButton mRbInform;
    @BindView(R.id.rb_remine_0) RadioButton mRbRemine0;
    @BindView(R.id.rb_remine_1) RadioButton mRbRemine1;
    @BindView(R.id.rb_remine_3) RadioButton mRbRemine3;
    @BindView(R.id.radioButton) RadioButton mRadioButton;
    @BindView(R.id.bt_add) Button mBtAdd;
    @BindView(R.id.tv_title) TextView mTvTitle;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerUserCenterComponent
                .builder()
                .appComponent(appComponent)
                .userCenterModule(new UserCenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView() {
        return R.layout.activity_usercenter;
    }

    @Override
    public void initData() {
        setupView();
        addListner();
    }

    private void addListner() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killMyself();
            }
        });
    }

    private void setupView() {
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_ab_back_holo_light_am);
        mTvTitle.setText("个人中心");
        setSupportActionBar(mToolbar);
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
        //1农历 2阳历  生日偏好
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
        Log.i("zmin.添加的生日.....","...." +  map);
        return map;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
