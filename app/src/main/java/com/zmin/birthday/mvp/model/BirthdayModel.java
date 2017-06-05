package com.zmin.birthday.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.api.service.UserService;
import com.zmin.birthday.mvp.model.entity.Birthday;
import com.zmin.birthday.mvp.model.entity.MovieEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by jess on 9/4/16 10:56
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
public class BirthdayModel extends BaseModel implements MainContract.Model {

    @Inject
    public BirthdayModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public List<Birthday> getBirthdayData() {
        return BirthdayData.getData();
    }

    @Override
    public Observable<MovieEntity> getUsers(int star, int end) {
        Observable<MovieEntity> topMovie = mRepositoryManager.obtainRetrofitService(UserService.class).getTopMovie(star,end);
        return topMovie;
    }

}
