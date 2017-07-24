package com.zmin.birthday.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zmin.birthday.mvp.contract.UserCenterContract;
import com.zmin.birthday.mvp.model.api.service.CommonService;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author: ZhangMin
 * @date: 2017/7/24 14:01
 * @desc:
 */
@ActivityScope
public class UserCenterModel extends BaseModel implements UserCenterContract.Model {
    @Inject
    public UserCenterModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<ResponseBeen> postBirthdayData(Map<String, Object> fields) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).addBirthdate(fields);
    }
}
