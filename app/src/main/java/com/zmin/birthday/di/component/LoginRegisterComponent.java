package com.zmin.birthday.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.zmin.birthday.di.module.LoginRegisterModule;
import com.zmin.birthday.mvp.ui.activity.LoginRegisterActivity;

import dagger.Component;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:51
 * @desc:
 */
@ActivityScope
@Component(modules = LoginRegisterModule.class,dependencies = AppComponent.class)
public interface LoginRegisterComponent {
    void inject(LoginRegisterActivity loginActivity);
}
