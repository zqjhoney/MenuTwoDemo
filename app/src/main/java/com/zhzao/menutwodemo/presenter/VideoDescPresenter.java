package com.zhzao.menutwodemo.presenter;

import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.entity.VideoHost;
import com.zhzao.menutwodemo.modle.VideoDescModle;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.view.VideoDescView;
import com.zzhao.utils.Base.BasePresenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/12/10.
 */

public class VideoDescPresenter  extends BasePresenter<VideoDescView> {
    private VideoDescModle modle;
    private VideoDescView view;
    private final Map<String, String> map;

    public VideoDescPresenter(VideoDescView view) {
        super(view);
        this.view=view;
        modle=new VideoDescModle();
        map = new HashMap<>();
    }


    public void getHodetVideo(String page){
        view.showLoading();
        map.put("page",page);
        modle.getHostMsg(Api.VIDEO_HOST, map, new MyCallback<VideoHost>() {
            @Override
            public void onFailure(Call call, IOException e) {
                view.hideLoading();
            }

            @Override
            public void onSuccess(ResponseBody responsebody, Object t) {
                view.success((VideoHost) t);
                view.hideLoading();
            }
            @Override
            public void onError(String errorMsg) {

            }
        });



    }

    public void getNearVideo(String latitude,String longitude,String page){


    }
}
