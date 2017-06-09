package com.zmin.birthday.mvp.model.api.service;

import com.zmin.birthday.app.userpermission.user.User;
import com.zmin.birthday.mvp.model.entity.MovieEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

    @POST("/sysapi/web.user.php")
    Observable<Object> register(@Body User user);
}
