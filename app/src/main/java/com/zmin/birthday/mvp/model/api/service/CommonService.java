package com.zmin.birthday.mvp.model.api.service;

import com.zmin.birthday.mvp.model.entity.BithdayBeen;
import com.zmin.birthday.mvp.model.entity.ResponseBeen;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 存放通用的一些API
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface CommonService {
    //添加生日
    @FormUrlEncoded
    @POST("sysapi/web.other.php")
    Observable<ResponseBeen> addBirthdate(@FieldMap Map<String, Object> fields);

    //获取备份生日
    @FormUrlEncoded
    @POST("sysapi/web.other.php")
    Observable<BithdayBeen> getBirthData(@FieldMap Map<String, Object> fields);

}
