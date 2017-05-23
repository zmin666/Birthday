package com.zmin.birthday.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.BirthdayModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:53
 * @desc:
 */
@Module
public class MainModule {

    private MainContract.view view;

    public MainModule(MainContract.view view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.view  provideMainView(){
        return view;
    }

    MainContract.Model provideMainModel(BirthdayModel model){
        return model;
    }
}
