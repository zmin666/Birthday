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
import com.zmin.birthday.app.utils.TimeUtil;
import com.zmin.birthday.di.component.DaggerAddBirthdayComponent;
import com.zmin.birthday.di.module.AddBirthdayModule;
import com.zmin.birthday.mvp.contract.AddBirthdayContract;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.presenter.AddBirthdayPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.zmin.birthday.R.id.et_date;
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
    @BindView(et_date) EditText mEtDate;
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
    /** 通过日历获取的日期 只有两种格式 y-m-d  或者 0000-m-d */
    private String mDate;
    /** 需要保存的年份 */
    private String mYear;
    /** 点击的位置 */
    private int mClickPosition;
    /** 点击item跳转过来 */
    private Birthday mBirthday;

    @OnTextChanged(value = et_date, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void changedYear() {
        String s = mEtDate.getText().toString().trim();
        String[] split = s.split("-");
        if (split.length == 3) {
            mYear = split[0];
        }
    }

    @OnClick(R.id.iv_back)
    public void onBack() {
        killMyself();
    }

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
        if (!checkDate()) return;

        String s = mEtDate.getText().toString().trim();
        String[] split = s.split("-");
        if (split.length == 2) {
            mDate = "".equals(mYear) ? "1900-" + s : mYear + "-" + s;
        } else if (split.length == 3) {
            mYear = split[0];
            mDate = s;
        }
        String[] splitRealDate = mDate.split("-");
        if (mCbignoreyear.isChecked()) {
            mEtDate.setText(splitRealDate[1] + "-" + splitRealDate[2]);
        } else {
            //如果没有输入年份,但是又不忽略年份, 默认使用2017年
            if ("1900".equals(splitRealDate[0])) {
                String t = "2017-" + splitRealDate[1] + "-" + splitRealDate[2];
                mDate = t;
            }
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
            mDate = mRbMale.isChecked() ? mBirthday.getOld_birth() : mBirthday.getBirth();
            mYear = mDate.split("-")[0];
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

        if (mCbignoreyear.isChecked()) {
            map.put("o_del_year", "2");
        } else {
            map.put("o_del_year", "1");
        }

        if (mRbSexMan.isChecked()) {
            map.put("o_sex", "1");
        } else if (mRbSexWoman.isChecked()) {
            map.put("o_sex", "2");
        }

        //1农历 2阳历

        String[] split = mDate.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);

        if (mRbMale.isChecked()) {
            map.put("o_prefer_brith", "1");
            map.put("o_lunar_birthday", mDate);
            //农历 --> 转成阳历
            LunarCalendar.Solar solar = LunarCalendar.lunarToSolar(new LunarCalendar.Lunar(day, month, year));
            map.put("o_solar_birthday", solar.solarYear + "-" + solar.solarMonth + "-" + solar.solarDay);
        } else if (mRbLunar.isChecked()) {
            map.put("o_prefer_brith", "2");
            map.put("o_solar_birthday", mDate);
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
        if (mCbignoreyear.isChecked()) {
            mEtDate.setText(TimeUtil.ignoreYear(date));
        } else {
            mEtDate.setText(date);
        }
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
        String ignoreYear = birthday.getIgnoreYear();
        if ("2".equals(ignoreYear)) {
            mCbignoreyear.setChecked(true);
        } else {
            mCbignoreyear.setChecked(false);
        }
        if (perfer.equals("1")) {
            mRbMale.setChecked(true);
            mRbLunar.setChecked(false);
            String s = "2".equals(ignoreYear) ? TimeUtil.ignoreYear(birthday.getOld_birth()) : birthday.getOld_birth();
            mEtDate.setText(s);
        } else {
            mRbLunar.setChecked(true);
            mRbMale.setChecked(false);
            String s = "2".equals(ignoreYear) ? TimeUtil.ignoreYear(birthday.getBirth()) : birthday.getBirth();
            mEtDate.setText(s);
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
    @Override
    public boolean checkDate() {
        String s = mEtDate.getText().toString().trim();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "生日日期不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        String[] split = s.split("-");
        if (split.length == 2 || split.length == 3) {
            try {
                int month = Integer.parseInt(split[split.length - 2]);
                int day = Integer.parseInt(split[split.length - 1]);
                if (month > 0 && month < 13 && day > 0 && day < 32) {
                    return true;
                } else {
                    Toast.makeText(this, "日期不符合规范 可以点击日历图标选择日期\n请参照 1991-10-24 或者 10-24 格式来写", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(this, "日期不符合规范 可以点击日历图标选择日期\n请参照 1991-10-24 或者 10-24 格式来写", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "日期不符合规范 可以点击日历图标选择日期\n请参照 1991-10-24 或者 10-24 格式来写", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
