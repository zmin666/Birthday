package com.zmin.birthday.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.zmin.birthday.di.module.AddBirthdayModule;
import com.zmin.birthday.mvp.ui.activity.AddBirthdayActivity;

import dagger.Component;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:51
 * @desc:
 */
@ActivityScope
@Component(modules = AddBirthdayModule.class, dependencies = AppComponent.class)
public interface AddBirthdayComponent {
    void inject(AddBirthdayActivity addBirthdayActivity);
}
