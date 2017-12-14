package com.zhzao.menutwodemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.zhzao.menutwodemo.common.App;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/28
 * decription:开发
 */

public class MyIntercepter implements Interceptor {
    private final String TAG = "respond";
private int versionCode;
    private Response response;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String token = SharePreUtils.getShareprefervalue("token");
        try {
            Context context = App.mContext;
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

if("GET".equals(request.method()))
{
    System.out.println("开始请求参数Get");
    HttpUrl.Builder builder = request.url().newBuilder();

    builder.addQueryParameter("source","android");
    builder.addQueryParameter("appVersion",String.valueOf(versionCode));
    builder.addQueryParameter("token", token+"");
    HttpUrl build = builder.build();
    request=request.newBuilder().url(build).build();

    if (NetStatus()==null){//无网络时候走缓存
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
    }
    response = chain.proceed(request);
    if (NetStatus()!=null) {// 有网络时 设置缓存超时时间为0;
        int maxAge = 0 * 60;
        response.newBuilder()
                .header("Cache-Control", "public, max-age=" + maxAge)
                .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .build();
    } else {
        int maxStale = 60 * 60 * 24; // 无网络时，设置超时为1天
        response.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .removeHeader("Pragma")
                .build();
    }
}
        if ("POST".equals(request.method())) {

            if (request.body() instanceof FormBody) {
                FormBody.Builder builder = new FormBody.Builder();
                FormBody body = (FormBody) request.body();

                for (int i = 0; i < body.size(); i++) {
                    builder.add(body.encodedName(i), body.encodedValue(i));
                }

                body = builder.add("source", "android")
                        .add("appVersion", String.valueOf(versionCode))
                        .add("token", token+"")
                        .build();
                request = request.newBuilder().post(body).build();

            }
            else if(request.body() instanceof MultipartBody)
            {
                MultipartBody body = (MultipartBody) request.body();
                MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("source","android")
                        .addFormDataPart("appVersion",versionCode+"")
                        .addFormDataPart("token",token+"");
                List<MultipartBody.Part> parts = body.parts();
                for (MultipartBody.Part part : parts) {
                    builder.addPart(part);
                }
                request=request.newBuilder().post(builder.build()).build();
            }

            response = chain.proceed(request);

        }
     return response;

    }

    //判断是否有网络
    public NetworkInfo NetStatus(){
        ConnectivityManager manager= (ConnectivityManager) App.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info;
    }


    /**
     * 添加公共参数
     *
     * @param oldRequest
     * @return
     */
    private Request addParam(Request oldRequest) {

        String token =SharePreUtils.getShareprefervalue("token");
        PackageInfo packageArchiveInfo = App.mContext.getPackageManager().getPackageArchiveInfo(App.mContext.getPackageName(), 0);
        int versionCode = packageArchiveInfo.versionCode;

        HttpUrl.Builder builder = oldRequest.url()
                .newBuilder()
                .setEncodedQueryParameter("source", "android")
                .setEncodedQueryParameter("token",token)
                .setEncodedQueryParameter("appVersion",versionCode+"")
                ;

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build();

        return newRequest;
    }


}
