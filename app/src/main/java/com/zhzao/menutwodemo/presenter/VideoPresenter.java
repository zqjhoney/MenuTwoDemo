package com.zhzao.menutwodemo.presenter;

import android.os.Environment;

import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.modle.VideoModle;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.VideoView;
import com.zzhao.utils.Base.BasePresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/12/1.
 */

public class VideoPresenter extends BasePresenter<VideoView> {
    private VideoModle modle;
    private VideoView view;
    public VideoPresenter(VideoView view) {
        super(view);
        this.view=view;
        modle=new VideoModle();
    }

    public void publicVideo(File files){
        String uid = SharePreUtils.getShareprefervalue("uid");
        if(uid==null || uid.equals("")){
            view.toast("uid不能为空");
            return;
        }
        view.showLoading();
        //封面
        File f=new File(Environment.getExternalStorageDirectory() .getAbsolutePath() + "/Tencent/QQ_Images/1511410355963.jpeg");

        RequestBody type=RequestBody.create(MediaType.parse("multipart/form_data"),f);//格式
        RequestBody fs=RequestBody.create(MediaType.parse("multipart/form_data"),files);//格式
        MultipartBody.Builder builder=new MultipartBody.Builder()
                .addFormDataPart("uid",uid)
                .addFormDataPart("latitude",45+"")
                .addFormDataPart("longitude",190+"")
                .addFormDataPart("videoFile",files.getName(),fs)
                .addFormDataPart("coverFile",f.getName(),type);//封面

        List<MultipartBody.Part> parts = builder.build().parts();

        modle.videoUpload(Api.VIDEO_PUBLIC, parts, new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                view.success(responseBody.string());
                view.hideLoading();
            }
        });


    }
}
