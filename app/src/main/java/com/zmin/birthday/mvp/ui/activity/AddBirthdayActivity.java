package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
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

    @OnClick(R.id.bt_add)
    public void onViewAddClicked() {
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
        if (mRbMale.isChecked()) {
            map.put("o_prefer_brith", "1");
        } else if (mRbLunar.isChecked()) {
            map.put("o_prefer_brith", "2");
        }
        String date = mEtDate.getText().toString().trim();
        map.put("o_solar_birthday", date);
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
