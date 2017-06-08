package com.zmin.birthday.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zmin.birthday.R;
import com.zmin.birthday.app.userpermission.permission.OperateRequesetRole;
import com.zmin.birthday.app.userpermission.permission.PermissionUtil;
import com.zmin.birthday.app.userpermission.user.User;
import com.zmin.birthday.app.userpermission.user.UserControl;

/**
 * @author: ZhangMin
 * @date: 2017/6/8 11:11
 * @desc:
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    public void test_login(View view) {
        new PermissionUtil().invoke(this, OperateRequesetRole.login, new PermissionUtil.ExecuteCallback() {
            @Override
            public void excute() {
                Toast.makeText(TestActivity.this, "登录权限通过", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void test_identance(View view) {
        new PermissionUtil().invoke(this, OperateRequesetRole.donate, new PermissionUtil.ExecuteCallback() {
            @Override
            public void excute() {
                Toast.makeText(TestActivity.this, "认证权限通过", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(View view) {
        User currentUser = UserControl.getInstance().getCurrentUser(this);
        currentUser.setUserId("12345");
        UserControl.getInstance().setUserToXml(this, currentUser);
    }

    public void identance(View view) {
        User currentUser = UserControl.getInstance().getCurrentUser(this);
        currentUser.setUserId("12345");
        currentUser.setDonate("1");
        UserControl.getInstance().setUserToXml(this, currentUser);
    }


    public void clear(View view) {
        User currentUser = UserControl.getInstance().getCurrentUser(this);
        currentUser.setUserId("0");
        currentUser.setDonate("0");
        UserControl.getInstance().setUserToXml(this, currentUser);
    }

}
