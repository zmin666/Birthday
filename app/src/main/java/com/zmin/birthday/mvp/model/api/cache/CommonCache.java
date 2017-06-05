package com.zmin.birthday.mvp.model.api.cache;

import com.zmin.birthday.mvp.model.entity.MovieEntity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Created by jess on 8/30/16 13:53
 * Contact with jess.yan.effort@gmail.com
 */
public interface CommonCache {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<MovieEntity>> getTopMovie(Observable<MovieEntity> movieEntityObservable, DynamicKey idLastUserQueried, EvictProvider evictProvider);


//    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
