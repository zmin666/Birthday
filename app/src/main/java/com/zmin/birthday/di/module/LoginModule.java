package com.zmin.birthday.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.zmin.birthday.mvp.contract.LoginContract;
import com.zmin.birthday.mvp.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:53
 * @desc:
 */
@Module
public class LoginModule {

    private LoginContract.view view;

    public LoginModule(LoginContract.view view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.view  provideView(){
        return view;
    }

    @ActivityScope
    @Provides
    LoginContract.Model provideModel(LoginModel model){
        return model;
    }
}
