package com.zhzao.menutwodemo.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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

    //post多参数
    @Headers("accept:*/*")
    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> requestPost(@Url String url, @FieldMap Map<String, String> params);


    //多文件多参数
    @Headers("accept:*/*")
    @Multipart
    @POST()
   // Observable<ResponseBody> upLoad(@Url String url, @FieldMap Map<String, Object> params);
    Observable<ResponseBody> upLoad(@Url String url,  @PartMap() Map<String, RequestBody> partMap,
                                    @Part List<MultipartBody.Part> files);
    //单文件单参数
    @Headers("accept:*/*")
    @Multipart
    @POST()
    Observable<ResponseBody> upSignLoad(@Url String url, @Part List<MultipartBody.Part> parts);

    //不能直接写@Field("uid") String param,，编码格式不一致
//    Observable<ResponseBody> upSignLoad(@Url String url, @Field("uid") String param,
//                                        @Part List<MultipartBody.Part> parts);



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
