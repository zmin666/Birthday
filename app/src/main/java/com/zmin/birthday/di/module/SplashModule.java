package com.zmin.birthday.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;
import com.zmin.birthday.mvp.contract.SplashContract;
import com.zmin.birthday.mvp.model.BirthdayModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:53
 * @desc:
 */
@Module
public class SplashModule {

    private SplashContract.view view;

    public SplashModule(SplashContract.view view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SplashContract.view provideSplashView() {
        return view;
    }

    @ActivityScope
    @Provides
    BaseModel provideMainModel(BirthdayModel model){
        return model;
    }

}
