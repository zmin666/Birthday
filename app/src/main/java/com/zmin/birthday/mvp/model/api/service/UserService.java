package com.zmin.birthday.mvp.model.api.service;

import com.zmin.birthday.mvp.model.entity.Loginer;
import com.zmin.birthday.mvp.model.entity.MovieEntity;
import com.zmin.birthday.mvp.model.entity.RegisterBeen;
import com.zmin.birthday.mvp.model.entity.RegisterRequestBeen;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

    //注册
    @POST("sysapi/web.user.php")
    Observable<RegisterBeen> register(@Body RegisterRequestBeen registerRequestBeen);

    //登录
    @POST("sysapi/web.user.php")
    Observable<Object> login(@Body Loginer loginer);
}
