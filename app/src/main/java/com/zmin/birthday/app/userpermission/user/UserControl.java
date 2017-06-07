package com.zmin.birthday.app.userpermission.user;

import android.content.Context;
import android.util.Log;

import com.zmin.birthday.app.userpermission.permission.RoleConfig;
import com.zmin.birthday.app.userpermission.permission.SPUtils;

import java.util.ArrayList;


/**
 * @author: ZhangMin
 * @date:  2017/6/7 16:13
 * @desc:  用户控制类,主要是获取当前的用户.修改用户信息,需要同时修改xml文件.以全局保存.
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

        if (id != null && !id.equals("0")) {
            list.add(RoleConfig.ROLE_LOGIN);
        }
        if (donate != null && !donate.equals("0")) {
            list.add(RoleConfig.ROLE_IDENTANCE);
        }
        if (isAssurance != null && isAssurance.equals("1")) {
            list.add(RoleConfig.ROLE_ASSURANCE);
        }
        user.setPermissions(list);
        //不影响权限的属性

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
        spUtils.putString(UserConstant.USER_ISIDENTIFY, user.getIsIdentify());
        spUtils.putString(UserConstant.USER_ISASSURANCE, user.getIsAssurance());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("," + RoleConfig.ROLE_LOGIN);
        if (user.getIsIdentify() != null && user.getIsIdentify().equals("1")) {
            stringBuilder.append("," + RoleConfig.ROLE_IDENTANCE);
        }
        if (user.getIsAssurance() != null && user.getIsAssurance().equals("1")) {
            stringBuilder.append("," + RoleConfig.ROLE_ASSURANCE);
        }
        spUtils.putString(UserConstant.USER_PERMISSIONS, stringBuilder.toString());

        Log.i("zmin.........权限...", "...." + stringBuilder.toString());
        //不影响权限的属性



    }
}
