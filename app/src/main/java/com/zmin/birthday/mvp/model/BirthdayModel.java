package com.zmin.birthday.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zmin.birthday.mvp.contract.MainContract;
import com.zmin.birthday.mvp.model.api.cache.CommonCache;
import com.zmin.birthday.mvp.model.api.service.CommonService;
import com.zmin.birthday.mvp.model.api.service.UserService;
import com.zmin.birthday.mvp.model.entity.BithdayBeen;
import com.zmin.birthday.mvp.model.entity.MovieEntity;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

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
    public Observable<BithdayBeen> getBirthdayData(Map<String, Object> fields) {
        return    mRepositoryManager.obtainRetrofitService(CommonService.class).getBirthData(fields);
    }

    @Override
    public Observable<MovieEntity> getUsers(int star, int end) {
        Observable<MovieEntity> topMovie = mRepositoryManager.obtainRetrofitService(UserService.class).getTopMovie(star,end);
        return mRepositoryManager
                .obtainCacheService(CommonCache.class)
                .getTopMovie(topMovie, new DynamicKey(end), new EvictDynamicKey(true))
                .flatMap(new Function<Reply<MovieEntity>, ObservableSource<MovieEntity>>() {
                    @Override
                    public ObservableSource<MovieEntity> apply(@NonNull Reply<MovieEntity> movieEntityReply) throws Exception {
                        return Observable.just(movieEntityReply.getData());
                    }
                });
    }

}
