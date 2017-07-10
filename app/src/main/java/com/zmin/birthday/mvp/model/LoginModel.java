package com.zmin.birthday.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zmin.birthday.mvp.contract.LoginRegisterContract;
import com.zmin.birthday.mvp.model.api.service.UserService;
import com.zmin.birthday.mvp.model.entity.LoginBeen;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by jess on 9/4/16 10:56
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
public class LoginModel extends BaseModel implements LoginRegisterContract.Model {

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<ResponseBeen> register(Map<String, Object> fields) {
        return mRepositoryManager.obtainRetrofitService(UserService.class).register(fields);
    }

    @Override
    public Observable<LoginBeen> login(Map<String, Object> fields) {
        return mRepositoryManager.obtainRetrofitService(UserService.class).login(fields);
    }
}
