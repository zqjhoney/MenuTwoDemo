package com.zhzao.menutwodemo.service;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by 张乔君 on 2017/11/17.
 */

public interface RetrofitHttpService {

//    @Headers("accept:*/*")
//    @GET("/{type}")
//    Call<ResponseBody> get(@Url String url, @QueryMap Map<String, String> params);
//
//    @Headers("accept:*/*")
//    @FormUrlEncoded
//    @POST()
//    Call<ResponseBody> post(@Url String url, @FieldMap Map<String, String> params);


    @Headers("accept:*/*")
    @GET()
    Observable<ResponseBody> RequestGet(@Url String url);

    @Headers("accept:*/*")
    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> requestPost(@Url String url, @FieldMap Map<String, String> params);


    @Headers("accept:*/*")
    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> upLoad(@Url String url, @FieldMap Map<String, Object> params);

//    @Headers("accept:*/*")
//    @Streaming
//    @GET()
//    Observable<ResponseBody> Obdownload( @Url String url, @QueryMap Map<String, String> params);
//
//    @Headers("accept:*/*")
//    @Streaming
//    @GET()
//    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, String> params);
}
