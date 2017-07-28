package com.zmin.birthday.mvp.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zmin.birthday.R;
import com.zmin.birthday.app.utils.LunarCalendar;
import com.zmin.birthday.di.component.DaggerAddBirthdayComponent;
import com.zmin.birthday.di.module.AddBirthdayModule;
import com.zmin.birthday.mvp.contract.AddBirthdayContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.presenter.AddBirthdayPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zmin.birthday.R.id.rb_male;

/**
 * @author: ZhangMin
 * @date: 2017/7/7 17:18
 * @desc:
 */
public class AddBirthdayActivity extends BaseActivity<AddBirthdayPresenter> implements AddBirthdayContract.view {


    @BindView(R.id.name) EditText mName;
    @BindView(R.id.rb_sex_man) RadioButton mRbSexMan;
    @BindView(R.id.rb_sex_woman) RadioButton mRbSexWoman;
    @BindView(rb_male) RadioButton mRbMale;
    @BindView(R.id.rb_lunar) RadioButton mRbLunar;
    @BindView(R.id.et_date) EditText mEtDate;
    @BindView(R.id.image_select_date) ImageView mImageSelectDate;
    @BindView(R.id.rb_remine_0) RadioButton mRbRemine0;
    @BindView(R.id.rb_remine_1) RadioButton mRbRemine1;
    @BindView(R.id.rb_remine_3) RadioButton mRbRemine3;
    @BindView(R.id.rb_note) RadioButton mRbNote;
    @BindView(R.id.rb_inform) RadioButton mRbInform;
    @BindView(R.id.fl_loading) FrameLayout mFlloading;
    @BindView(R.id.ll_botton) LinearLayout mLlBotton;
    @BindView(R.id.bt_add) Button mBtadd;
    @BindView(R.id.cb_ignore_year) CheckBox mCbignoreyear;
    /** 通过日历获取的日期 只有两种格式 y-m-d  或者 m-d */
    private String mDate;
    /** 点击的位置 */
    private int mClickPosition;
    /** 点击item跳转过来 */
    private Birthday mBirthday;


    @OnClick(R.id.bt_add)
    public void onViewAddClicked() {
        if (checkDate()) {
            showLoading();
            mPresenter.uploadeData("1", -1);
        }
    }

    @OnClick(R.id.bt_delete)
    public void onDelete() {
        showLoading();
        mPresenter.setBirthday(mBirthday);
        mPresenter.uploadeData("4", mClickPosition);
    }

    @OnClick(R.id.bt_update)
    public void onSave() {
        if (checkDate()) {
            showLoading();
            mPresenter.setBirthday(mBirthday);
            mPresenter.uploadeData("5", mClickPosition);
        }
    }

    @OnClick(R.id.image_select_date)
    public void onViewClicked() {
        selectDate();
    }

    @OnClick(R.id.cb_ignore_year)
    public void onIgnoreYear() {
        String s = mEtDate.getText().toString().trim();
        String[] split = s.split("-");
        if (split.length == 2) {
            String[] dateSplit = mDate.split("-");
            mDate = dateSplit[0] + "-" + s;
        } else if (split.length == 3) {
            mDate = s;
        } else {
            Toast.makeText(this, "日期不符合规范 可以点击日历图标选择日期\n请参照 1991-10-24 或者 10-24 格式来写", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mCbignoreyear.isChecked()) {
            if (!TextUtils.isEmpty(s)) {
                if (split.length == 3 && split[0].length() == 4) {
                    mEtDate.setText(split[1] + "-" + split[2]);
                }
            }
        } else {
            mEtDate.setText(mDate);
        }
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
        mBirthday = getIntent().getExtras().getParcelable("birthday");
        if (mBirthday != null) {
            mClickPosition = getIntent().getExtras().getInt("position");
            mLlBotton.setVisibility(View.VISIBLE);
            mBtadd.setVisibility(View.GONE);
            showDate(mBirthday);
        } else {
            mBtadd.setVisibility(View.VISIBLE);
            mLlBotton.setVisibility(View.GONE);
        }

        mEtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    onIgnoreYear();
                }
            }
        });
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
        Log.i("zmin.添加的生日.....", "...." + map);
        return map;
    }

    @Override
    public void selectDate() {
        mPresenter.calendar();
    }

    @Override
    public void setDate(String date) {
        this.mDate = date;
        mEtDate.setText(date);
    }

    @Override
    public void showDate(Birthday birthday) {
        mName.setText(birthday.getName());
        String sex = birthday.getSex();
        if (sex.equals("1")) {
            mRbSexMan.setChecked(true);
            mRbSexWoman.setChecked(false);
        } else {
            mRbSexWoman.setChecked(true);
            mRbSexMan.setChecked(false);
        }
        String perfer = birthday.getPerfer();
        if (perfer.equals("1")) {
            mRbMale.setChecked(true);
            mRbLunar.setChecked(false);
            mEtDate.setText(birthday.getOld_birth());
        } else {
            mRbLunar.setChecked(true);
            mRbMale.setChecked(false);
            mEtDate.setText(birthday.getBirth());
        }
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

    /**
     * 检查时间格式
     *
     * @return
     */
    private boolean checkDate() {
        String s = mEtDate.getText().toString().trim();
        String[] split = s.split("-");
        if (split.length == 2 || split.length == 3) {
            return true;
        } else {
            Toast.makeText(this, "日期不符合规范 可以点击日历图标选择日期\n请参照 1991-10-24 或者 10-24 格式来写", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
