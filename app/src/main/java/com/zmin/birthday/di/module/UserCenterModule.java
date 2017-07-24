package com.zmin.birthday.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.zmin.birthday.mvp.contract.UserCenterContract;
import com.zmin.birthday.mvp.model.UserCenterModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:53
 * @desc:
 */
@Module
public class UserCenterModule {

    private UserCenterContract.view view;

    public UserCenterModule(UserCenterContract.view view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserCenterContract.view provideView() {
        return view;
    }

    @ActivityScope
    @Provides
    UserCenterContract.Model provideModel(UserCenterModel model) {
        return model;
    }
}
