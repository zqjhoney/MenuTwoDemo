package com.zhzao.menutwodemo.presenter;

import android.text.TextUtils;

import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.entity.VideoBean;
import com.zhzao.menutwodemo.modle.ShowVideoModle;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.ShowVideoView;
import com.zzhao.utils.Base.BasePresenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/12/2.
 */

public class ShowVideoPresenter extends BasePresenter<ShowVideoView> {
    private ShowVideoView view;
    private ShowVideoModle modle;
    private final Map<String, String> map;

    public ShowVideoPresenter(ShowVideoView view) {
        super(view);
        this.view=view;
        modle=new ShowVideoModle();
        map = new HashMap<>();
    }

    public void getVideo(String type,String page){
        String uid = SharePreUtils.getShareprefervalue("uid");
        if(TextUtils.isEmpty(uid)){
            view.toast("uid不能为空");
            return;
        }
        map.put("uid",uid);
        map.put("type",type);
        map.put("page",page);
        view.showLoading();//展示activitybar
        modle.getVideoMsg(Api.VIDEO_GET, map, new MyCallback<VideoBean>() {
            @Override
            public void onFailure(Call call, IOException e) {
                view.hideLoading();
                view.failure("showView异常"+e);
            }
            @Override
            public void onSuccess(ResponseBody responsebody, Object t) {
                view.hideLoading();
                view.success((VideoBean) t);
            }

            @Override
            public void onError(String errorMsg) {
            }
        });




    }



}
