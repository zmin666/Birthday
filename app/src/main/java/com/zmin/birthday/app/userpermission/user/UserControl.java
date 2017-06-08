package com.zmin.birthday.app.userpermission.user;

import android.content.Context;

import com.zmin.birthday.app.userpermission.permission.RoleConfig;
import com.zmin.birthday.app.userpermission.permission.SPUtils;

import java.util.ArrayList;


/**
 * @author: ZhangMin
 * @date: 2017/6/7 16:13
 * @desc: 用户控制类, 主要是获取当前的用户.修改用户信息, 需要同时修改xml文件.以全局保存.
 */
public class UserControl {

    private static final String USER_CONSTANT = "user_id_";
    private static UserControl mUserControl;

    private UserControl() {
    }

    public static UserControl getInstance() {
        if (mUserControl == null) {
            synchronized (UserControl.class) {
                if (mUserControl == null) {
                    mUserControl = new UserControl();
                }
            }
        }
        return mUserControl;
    }


    /**
     * 获取当前用户
     * xml --> user
     *
     * @param context
     */

    public static final String USER_ISIDENTIFY = "user_isIdentify";
    public static final String USER_ISASSURANCE = "user_isAssurance";


    public User getCurrentUser(Context context) {
        //获取当前用户
        SPUtils spUtils = new SPUtils(context, UserConstant.USER_XML);
        User user = new User();
        //影响权限的属性
        String id = spUtils.getString(UserConstant.USER_ID);
        String donate = spUtils.getString(UserConstant.USER_DONATE);
        user.setUserId(id);
        user.setDonate(donate);
        ArrayList<String> list = new ArrayList<>();
        if(id != null && !id.equals("0")){
            list.add(RoleConfig.ROLE_LOGIN);
        }
        if(donate != null && donate.equals("1")){
            list.add(RoleConfig.ROLE_DONATE);
        }
        user.setPermission(list);

        //不影响权限的属性
        String user_name = spUtils.getString(UserConstant.USER_NAME);
        String user_password = spUtils.getString(UserConstant.USER_PASSWORD);
        String user_preferdata = spUtils.getString(UserConstant.USER_PREFERDATA);
        String user_phonenum = spUtils.getString(UserConstant.USER_PHONENUM);
        String user_old_year = spUtils.getString(UserConstant.USER_OLD_YEAR);
        String user_old_month = spUtils.getString(UserConstant.USER_OLD_MONTH);
        String user_old_day = spUtils.getString(UserConstant.USER_OLD_DAY);
        String user_year = spUtils.getString(UserConstant.USER_YEAR);
        String user_month = spUtils.getString(UserConstant.USER_MONTH);
        String user_day = spUtils.getString(UserConstant.USER_DAY);

        user.setName(user_name);
        user.setPassword(user_password);
        user.setPreferData(user_preferdata);
        user.setPhoneNum(user_phonenum);

        user.setOld_year(user_old_year);
        user.setOld_month(user_old_month);
        user.setOld_day(user_old_day);
        user.setYear(user_year);
        user.setMonth(user_month);
        user.setDay(user_day);

        return user;
    }

    /**
     * user属性变化就需要调用这个方法持久化保存在xml中
     * user --> xml
     *
     * @param context
     * @param user
     */
    public void setUserToXml(Context context, User user) {
        SPUtils spUtils = new SPUtils(context, UserConstant.USER_XML);
        String userId = user.getUserId();
        if (userId == null || userId.equals("0")) {
            spUtils.clear();
            return;
        }

        //影响权限的属性
        spUtils.putString(UserConstant.USER_ID, userId);
        spUtils.putString(UserConstant.USER_DONATE, user.getDonate());

        //不影响权限的属性
        spUtils.putString(UserConstant.USER_NAME, user.getName());
        spUtils.putString(UserConstant.USER_PASSWORD, user.getPassword());
        spUtils.putString(UserConstant.USER_PREFERDATA, user.getPreferData());
        spUtils.putString(UserConstant.USER_PHONENUM, user.getPhoneNum());
        spUtils.putString(UserConstant.USER_OLD_YEAR, user.getOld_year());
        spUtils.putString(UserConstant.USER_OLD_MONTH, user.getOld_month());
        spUtils.putString(UserConstant.USER_OLD_DAY, user.getOld_day());
        spUtils.putString(UserConstant.USER_YEAR, user.getYear());
        spUtils.putString(UserConstant.USER_MONTH, user.getMonth());

    }
}
