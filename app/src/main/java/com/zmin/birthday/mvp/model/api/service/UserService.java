package com.zmin.birthday.mvp.model.api.service;

import com.zmin.birthday.mvp.model.entity.Loginer;
import com.zmin.birthday.mvp.model.entity.MovieEntity;
import com.zmin.birthday.mvp.model.entity.RegisterBeen;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

    //注册
    @FormUrlEncoded
    @POST("sysapi/web.user.php")
    Observable<RegisterBeen> register(@Field("username") String username, @Field("pwd") String pwd);

    //登录
    @POST("sysapi/web.user.php")
    Observable<Object> login(@Body Loginer loginer);
}
