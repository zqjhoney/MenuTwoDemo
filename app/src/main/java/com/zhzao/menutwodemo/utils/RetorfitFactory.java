package com.zhzao.menutwodemo.utils;

import android.os.Environment;
import android.os.Handler;

import com.google.gson.Gson;
import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.service.RetrofitHttpService;
import com.zhzao.menutwodemo.trustbooks.TrustAllCerts;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 张乔君 on 2017/11/17.
 */

public class RetorfitFactory {
    /**
     * 单利模式
     */
    private static RetorfitFactory httpUtils;
    private final Retrofit mRetrofit;
    private static Gson gson;
    private static final long cacheSize = 1024 * 1024 * 20;// 缓存文件最大限制大小20M
    private static String cacheDirectory = Environment.getExternalStorageDirectory() + "/okttpcaches"; // 设置缓存文件路径
    private static Cache cache = new Cache(new File(cacheDirectory), cacheSize);  //

    private RetorfitFactory() {

        OkHttpClient mOkHttpClient=new OkHttpClient.Builder()
                .connectTimeout(60000, TimeUnit.SECONDS)
                .readTimeout(60000, TimeUnit.SECONDS)
                .writeTimeout(60000, TimeUnit.SECONDS)
               // .addInterceptor(InterceptorUtil.HeaderInterceptor())
//                .sslSocketFactory(createSSLSocketFactory())
//                .hostnameVerifier(new TrustAllHostnameVerifier())
                .retryOnConnectionFailure(false)
                .cache(cache)
                .addInterceptor(new MyIntercepter())//添加日志拦截器
                .build();

        //添加gson转换器
        //添加rxjava转换器
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build();
    }

    public static RetorfitFactory getInstance(){
        if(httpUtils==null)
        {
            synchronized (RetorfitFactory.class){
                if(httpUtils==null){
                    httpUtils=new RetorfitFactory();
                    gson = new Gson();
                }
            }
        }
        return  httpUtils;
    }


    //多个参数得到Observer
    public void getObserver(String path, Map<String,String> map,Observer<ResponseBody> observer){
        if(map!=null && map.size()>0){//走post请求
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            Observable<ResponseBody> observable1 = service.requestGet(path, map);
            observable1.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(observer);
        }else{//get请求
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            Observable<ResponseBody> observable1 = service.RequestGet(path);

            observable1.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(observer);
        }
    }
    //实例成对象回调回去
    //多个参数得到自己的回调接口Callback
    public void getObserver(String path, Map<String,String> map, final MyCallback callback){
        if(map!=null && map.size()>0){//走post请求
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            Observable<ResponseBody> observable1 = service.requestGet(path, map);

            observable1.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(ResponseBody value) {
                            try {
                                String msg=value.string();
                                Object o = gson.fromJson(msg, callback.type);
                                callback.onSuccess(value,o);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onError(Throwable e) {
                                    callback.onError("异常"+e);
                        }
                        @Override
                        public void onComplete() {
                        }
                    });

        }else{//get请求
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            Observable<ResponseBody> observable1 = service.RequestGet(path);
            observable1.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }
                        @Override
                        public void onNext(ResponseBody value) {
                            try {
                                String msg=value.string();
                                Object o = gson.fromJson(msg, callback.type);
                                callback.onSuccess(value,o);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onError(Throwable e) {
                            callback.onError("异常"+e);
                        }
                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }
    //多个参数得到consumer
    public void getConsumer(String path, Map<String,String> map, Consumer<ResponseBody> consumer){
        if(map!=null && map.size()>0)
        {
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            service.requestPost(path,map)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(consumer);
        }else{
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            service.RequestGet(path)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(consumer);
        }
    }
    //多个参数得到自己的Callback
    public void getConsumer(String path, Map<String,String> map, final MyCallback callback){
        if(map!=null && map.size()>0)
        {
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            service.requestPost(path,map)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            String string = responseBody.string();
                            Object o = gson.fromJson(string, callback.type);
                            callback.onSuccess(responseBody,o);

                        }
                    });
        }else{
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            service.RequestGet(path)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            String string = responseBody.string();
                            Object o = gson.fromJson(string, callback.type);
                            callback.onSuccess(responseBody,o);

                        }
                    });
        }
    }
    //单文件单参数得到consumer
    public void uploadConsumer(String path, List<MultipartBody.Part> part, Consumer<ResponseBody> consumer){
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            service.upSignLoad(path,part)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(consumer);
    }
    //多文件多参数得到consumer
    public void uploadConsumer(String path, Map<String, RequestBody> map, List<MultipartBody.Part> list, Consumer<ResponseBody> consumer){
        if(map!=null && map.size()>0)
        {
            RetrofitHttpService service = mRetrofit.create(RetrofitHttpService.class);
            service.upLoad(path,map,list)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(consumer);
        }else{
            new Throwable("传入的map不能为空哦");
        }
    }

    //ssl加密
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }


}
