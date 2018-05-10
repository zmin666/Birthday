package com.zmin.birthday.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.zmin.birthday.mvp.contract.RegisterContract;
import com.zmin.birthday.mvp.model.RegisterModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:53
 * @desc:
 */
@Module
public class RegisterModule {

    private RegisterContract.view view;

    public RegisterModule(RegisterContract.view view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RegisterContract.view  provideView(){
        return view;
    }

    @ActivityScope
    @Provides
    RegisterContract.Model provideModel(RegisterModel model){
        return model;
    }
}
