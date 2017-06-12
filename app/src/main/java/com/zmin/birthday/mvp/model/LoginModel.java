package com.zmin.birthday.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zmin.birthday.mvp.contract.LoginContract;
import com.zmin.birthday.mvp.model.api.service.UserService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by jess on 9/4/16 10:56
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public void register() {

    }

    @Override
    public Observable login(String userName, String pwd) {
        return mRepositoryManager.obtainRetrofitService(UserService.class).login(userName, pwd);
    }
}
