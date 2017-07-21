package com.zmin.birthday.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;
import com.zmin.birthday.R;
import com.zmin.birthday.app.utils.TimeUtil;
import com.zmin.birthday.mvp.model.entity.Birthday;

import java.util.List;


/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:34
 * @desc:
 */
public class BirthdayDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_DATA = 0;
    private static final int TYPE_NULL = 1;


    List<Birthday> mBirthdayList;
    Context mContext;

    public BirthdayDataAdapter(List<Birthday> birthdayList, Context context) {
        mBirthdayList = birthdayList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_DATA) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
            return new BirthdayHolder(view);
        } else if (viewType == TYPE_NULL) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_null, parent, false);
            return new BirthdayNullHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BirthdayHolder) {
            Birthday birthday = mBirthdayList.get(position);
            BirthdayHolder birthdayHolder = (BirthdayHolder) holder;
            birthdayHolder.tv_name.setText("昵称: " + birthday.getName());
            String perfer = birthday.getPerfer();
            if ("1".equals(perfer)) {
                birthdayHolder.tv_perfer.setText("农历生日");
            } else if ("2".equals(perfer)) {
                birthdayHolder.tv_perfer.setText("阳历生日");
            } else if ("3".equals(perfer)) {
                birthdayHolder.tv_perfer.setText("两个生日");
            }
            String birth = birthday.getBirth();
            birthdayHolder.tv_old_birthday.setText("农历: " + birthday.getOld_birth());
            birthdayHolder.tv_new_birthday.setText("阳历: " + birth);
            //倒计时
            birthdayHolder.tv_num.setText(TimeUtil.getCountdown(birth));
        } else if (holder instanceof BirthdayNullHolder) {

        }
    }


    @Override
    public int getItemCount() {
        return mBirthdayList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mBirthdayList.size()) {
            return TYPE_DATA;
        } else {
            return TYPE_NULL;
        }
    }

    class BirthdayHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_perfer;
        TextView tv_old_birthday;
        TextView tv_new_birthday;
        TextView tv_num;

        public BirthdayHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_perfer = (TextView) itemView.findViewById(R.id.tv_perfer);
            tv_old_birthday = (TextView) itemView.findViewById(R.id.tv_old_birthday);
            tv_new_birthday = (TextView) itemView.findViewById(R.id.tv_new_birthday);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            AutoUtils.autoSize(itemView);
        }
    }

    class BirthdayNullHolder extends RecyclerView.ViewHolder {
        public BirthdayNullHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
        }
    }
}
