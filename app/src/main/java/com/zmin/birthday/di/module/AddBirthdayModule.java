package com.zmin.birthday.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.zmin.birthday.mvp.contract.AddBirthdayContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:53
 * @desc:
 */
@Module
public class AddBirthdayModule {

    private AddBirthdayContract.view view;

    public AddBirthdayModule(AddBirthdayContract.view view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddBirthdayContract.view  provideView(){
        return view;
    }

    @ActivityScope
    @Provides
    AddBirthdayContract.Model provideModel(AddBirthdayContract.Model model){
        return model;
    }
}
