package com.zmin.birthday.mvp.model.api.service;

import com.zmin.birthday.mvp.model.entity.Loginer;
import com.zmin.birthday.mvp.model.entity.MovieEntity;
import com.zmin.birthday.mvp.model.entity.RegisterBeen;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
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
    Observable<RegisterBeen> register(@FieldMap Map<String, Object> fields);

    //登录
    @POST("sysapi/web.user.php")
    Observable<Object> login(@Body Loginer loginer);
}
