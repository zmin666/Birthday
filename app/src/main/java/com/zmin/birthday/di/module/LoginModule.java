package com.zmin.birthday.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.zmin.birthday.mvp.contract.LoginRegisterContract;
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

    private LoginRegisterContract.view view;

    public LoginModule(LoginRegisterContract.view view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginRegisterContract.view  provideView(){
        return view;
    }

    @ActivityScope
    @Provides
    LoginRegisterContract.Model provideModel(LoginModel model){
        return model;
    }
}
