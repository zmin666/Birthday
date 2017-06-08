package com.zmin.birthday.app.userpermission.permission;

import android.content.Context;
import android.util.Log;

import com.zmin.birthday.app.userpermission.user.User;
import com.zmin.birthday.app.userpermission.user.UserControl;

import java.util.List;


/**
 * @author: ZhangMin
 * @date: 2017/6/7 16:15
 * @desc: 使用这个类来判断权限(登录, 认证, 保证金资格)
 */
public class PermissionUtil {

    private Context context;

    public interface ExecuteCallback {
        void excute();
    }

    ExecuteCallback mExecuteCallback;

    public void invoke(Context context, OperateRequesetRole operate, ExecuteCallback executeCallback) {
        this.context = context;
        //根据认证状态显示提示内容
        User currentUser = UserControl.getInstance().getCurrentUser(context);
        List<String> userPermissions = currentUser.getPermission();  //当前用户的权限
        List<String> list = operate.getList(); //操作所需的角色集合
        this.mExecuteCallback = executeCallback;
        judgeAuthority(userPermissions, list);
    }

    private boolean judgeAuthority(List<String> userPermissions, List<String> list) {
        boolean hasPermission = true;
        for (String role : list) {
            if (!userPermissions.contains(role)) {
                hasPermission = false;
                getAuthority(role);
                break;
            }
        }
        if (hasPermission) {
            mExecuteCallback.excute();
        }
        return hasPermission;
    }

    /**
     * 缺少权限---> 获取对应权限
     */
    private void getAuthority(String currentRole) {
        switch (currentRole) {
            case RoleConfig.ROLE_LOGIN:  //缺少登录权限---> 去登录
                Log.i("zmin.............", "..缺少登录权限.去登录.");
                break;
            case RoleConfig.ROLE_DONATE:  //缺少捐赠的权限
                Log.i("zmin.............", "..缺少捐赠的权限.去认证.");
                break;
            default:
                break;
        }
    }
}
