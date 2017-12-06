package com.zhzao.menutwodemo.presenter;

import android.text.TextUtils;

import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.entity.UserInfo;
import com.zhzao.menutwodemo.modle.PerModle;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.PerView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/30.
 */

public class Per_presenter {
    private PerModle modle;
    private PerView view;
    private final Map<String, String> map;

    public Per_presenter( PerView view) {
        this.view = view;
        modle=new PerModle();
        map = new HashMap<>();
    }

    //获取用户信息
    public void getPerMsg(){
        String uid= SharePreUtils.getShareprefervalue("uid");
        if(TextUtils.isEmpty(uid)){
            view.toast("uid不能为空");
            return;
        }
        map.put("uid",uid);
        modle.getPerMsg(Api.GET_PerMsg, map, new MyCallback<UserInfo>() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.getLogger("异常"+e);
                view.failure("错误"+e);
            }
            @Override
            public void onSuccess(ResponseBody responsebody, Object t) {
                view.success((UserInfo) t);
            }
            @Override
            public void onError(String errorMsg) {
                    view.failure(errorMsg);
            }
        });
    }

    //上传头像
    public void upPerPhoto(File file){
        String uid= SharePreUtils.getShareprefervalue("uid");
        if(TextUtils.isEmpty(uid)){
            view.toast("uid不能为空");
            return;
        }
        MultipartBody.Builder builder=new MultipartBody.Builder()
                .addFormDataPart("uid",uid);
        if(file!=null){
            RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
            builder.addFormDataPart("file",file.getName(),requestBody);
        }
        List<MultipartBody.Part> parts = builder.build().parts();


        modle.upPhoto(Api.UP_Photo, uid, parts, new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                view.success(responseBody.string());
            }
        });
    }


}
