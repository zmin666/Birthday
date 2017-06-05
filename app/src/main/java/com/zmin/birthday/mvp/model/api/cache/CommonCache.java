package com.zmin.birthday.mvp.model.api.cache;

import com.zmin.birthday.mvp.model.entity.GankEntity;

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
    Observable<Reply<GankEntity>> getTopMovie(Observable<GankEntity> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}
