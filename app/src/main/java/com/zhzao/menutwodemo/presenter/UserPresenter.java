package com.zhzao.menutwodemo.presenter;

import android.text.TextUtils;

import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.entity.UserLie;
import com.zhzao.menutwodemo.modle.UserModle;
import com.zhzao.menutwodemo.utils.MyCallback;
import com.zhzao.menutwodemo.view.UserView;
import com.zzhao.utils.Base.BasePresenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/12/18.
 */

public class UserPresenter extends BasePresenter<UserView> {
    private UserView view;
    private UserModle modle;
    private final Map<String, String> map;

    public UserPresenter(UserView view) {
        super(view);
        this.view=view;
        modle=new UserModle();
        map = new HashMap<>();
    }

    public void getMsg(String uuid,String page){
        if(TextUtils.isEmpty(uuid)){
            view.toast("uuid为空");
            return;
        }
        map.put("page",page);
        map.put("uid",uuid);
        modle.getUserVideos(Api.USER_VIDEO, map, new MyCallback<UserLie>() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onSuccess(ResponseBody responsebody, Object t) {
                    view.success((UserLie) t);
            }

            @Override
            public void onError(String errorMsg) {
            }
        });

    }
}
